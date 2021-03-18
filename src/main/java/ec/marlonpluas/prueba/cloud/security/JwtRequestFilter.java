package ec.marlonpluas.prueba.cloud.security;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.marlonpluas.prueba.cloud.entity.InfoTransaccion;
import ec.marlonpluas.prueba.cloud.enums.Estado;
import ec.marlonpluas.prueba.cloud.repository.InfoTransaccionRepository;
import ec.marlonpluas.prueba.cloud.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Request filter de las peticiones
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header.token}")
    private String jwtHeaderToken;

    @Value("${jwt.header.apiKey}")
    private String jwtHeaderApiKey;

    @Value("${jwt.apiKey.secret}")
    private String jwtApiKeySecret;

    @Autowired
    private InfoTransaccionRepository infoTransaccionRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {
        String tokenHeader = request.getHeader(jwtHeaderToken);
        String apiKeyHeader = request.getHeader(jwtHeaderApiKey);

        String username = null;
        String jwtToken = null;

        // Verificar el apiKey
        if (apiKeyHeader == null) {
            throw new AuthenticationException("ApiKey es requerido");
        } else {
            if (!apiKeyHeader.equals(jwtApiKeySecret)) {
                throw new AuthenticationException("ApiKey incorrecto");
            }
        }

        // Verificar el token
        if (tokenHeader != null) {
            try {
                jwtToken = tokenHeader;
                username = jwtTokenUtil.getUsernameToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new AuthenticationException("No se puede obtener el token JWT");
            } catch (ExpiredJwtException e) {
                throw new AuthenticationException("El token JWT ha caducado");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Validar token y transacción
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String idTransaccion = jwtTokenUtil.getIdTransaccion(jwtToken);
            // Actualizar estado de la transacción
            Optional<InfoTransaccion> opTransaccion = infoTransaccionRepo.findById(idTransaccion);
            if (opTransaccion.isPresent() && opTransaccion.get().getEstado().equalsIgnoreCase(Estado.Activo.toString())) {
                InfoTransaccion transaccion = opTransaccion.get();
                transaccion.setEstado(Estado.Inactivo.toString());
                transaccion.setUsrModificacion(username);
                transaccion.setFeModificacion(new Date());
                infoTransaccionRepo.save(transaccion);
            } else {
                throw new AuthenticationException("Transacción ya fue realizada");
            }

            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
