package ec.marlonpluas.prueba.cloud.security.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.marlonpluas.prueba.cloud.dto.AuthResDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Clase AuthException, exception personalizada
 *
 * @author Marlon Pluas
 * @version 1.0.0
 */
public class AuthException implements AuthenticationEntryPoint, AccessDeniedHandler {
    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
    throws IOException {
        log.error("Acceso denegado " + request.getRequestURI());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        AuthResDTO resException = new AuthResDTO();
        resException.setMessage("Acceso denegado");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), resException);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("Acceso denegado 2 " + request.getRequestURI());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        AuthResDTO resException = new AuthResDTO();
        resException.setMessage("Acceso denegado");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), resException);
    }
}
