package ec.marlonpluas.prueba.cloud.dto;

import lombok.Data;

/**
 * DTO PruebaCloudReqDTO
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Data
public class PruebaCloudReqDTO {
    private String message;
    private String to;
    private String from;
    private Integer timeToLifeSec;
}
