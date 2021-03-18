package ec.marlonpluas.prueba.cloud.security.controller;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import ec.marlonpluas.prueba.cloud.entity.InfoTransaccion;
import ec.marlonpluas.prueba.cloud.enums.Estado;
import ec.marlonpluas.prueba.cloud.repository.InfoTransaccionRepository;
import ec.marlonpluas.prueba.cloud.security.JwtTokenUtil;
import ec.marlonpluas.prueba.cloud.security.dto.JwtRequest;
import ec.marlonpluas.prueba.cloud.security.dto.JwtResponse;
import ec.marlonpluas.prueba.cloud.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Jwt Authentication
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@RestController
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private InfoTransaccionRepository infoTransaccionRepo;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        // Validar idTransaccion
        String idTransaccion = UUID.randomUUID().toString();
        boolean transaccionOcupado = true;
        while (transaccionOcupado) {
            Optional<InfoTransaccion> opTransaccion = infoTransaccionRepo.findById(idTransaccion);
            if (opTransaccion.isPresent()) {
                idTransaccion = UUID.randomUUID().toString();
            } else {
                transaccionOcupado = false;
            }
        }

        final String token = jwtTokenUtil.generateToken(userDetails, idTransaccion);
        // Guardar idTransaccion
        InfoTransaccion transaccion = new InfoTransaccion();
        transaccion.setIdTransaccion(idTransaccion);
        transaccion.setUsername(authenticationRequest.getUsername());
        transaccion.setEstado(Estado.Activo.toString());
        transaccion.setFeCreacion(new Date());
        infoTransaccionRepo.save(transaccion);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Usuario desactivado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales inválidas", e);
        }
    }
}
