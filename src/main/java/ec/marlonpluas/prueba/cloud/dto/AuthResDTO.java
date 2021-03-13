package ec.marlonpluas.prueba.cloud.dto;

import lombok.Data;

/**
 * Clase DTO AuthResDTO
 *
 * @author Marlon Pluas
 * @version 1.0.0
 */
@Data
public class AuthResDTO {
    private int code = 401;
    private String status = "ERROR";
    private String message;
}
