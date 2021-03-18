package ec.marlonpluas.prueba.cloud.security.dto;

import lombok.Data;

/**
 * Clase DTO JwtRequest
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Data
public class JwtRequest {
    private String username;
    private String password;
}
