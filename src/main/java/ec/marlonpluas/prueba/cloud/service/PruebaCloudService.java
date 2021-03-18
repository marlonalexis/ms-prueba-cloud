package ec.marlonpluas.prueba.cloud.service;

import org.springframework.stereotype.Service;

import ec.marlonpluas.prueba.cloud.dto.PruebaCloudReqDTO;

/**
 * Service Prueba Cloud
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Service
public class PruebaCloudService {
    public String prueba(PruebaCloudReqDTO request) {
        return "Hello " + request.getTo() + " your message will be send";
    }
}
