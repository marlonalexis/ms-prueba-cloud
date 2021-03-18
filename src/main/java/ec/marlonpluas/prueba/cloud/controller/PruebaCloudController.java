package ec.marlonpluas.prueba.cloud.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.marlonpluas.prueba.cloud.dto.PruebaCloudReqDTO;
import ec.marlonpluas.prueba.cloud.dto.PruebaCloudResDTO;
import ec.marlonpluas.prueba.cloud.service.PruebaCloudService;

/**
 * Controller Prueba Cloud
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@RestController
public class PruebaCloudController {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    PruebaCloudService pruebaCloudService;

    @PostMapping(path = "DevOps", consumes = "application/json")
    public PruebaCloudResDTO devOps(@RequestBody PruebaCloudReqDTO request) {
        log.info("Petición recibida: DevOps");
        PruebaCloudResDTO response = new PruebaCloudResDTO();
        response.setMessage(pruebaCloudService.prueba(request));
        return response;
    }
}
