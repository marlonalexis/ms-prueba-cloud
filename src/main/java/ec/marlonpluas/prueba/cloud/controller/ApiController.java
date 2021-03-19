package ec.marlonpluas.prueba.cloud.controller;

import ec.marlonpluas.prueba.cloud.entity.AdmiApi;
import ec.marlonpluas.prueba.cloud.service.ApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller Api Key
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@RestController
@RequestMapping("api")
public class ApiController {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private ApiService apiService;

    @GetMapping("/listar")
    public List<AdmiApi> listar() {
        log.info("Petición recibida: listar");
        return apiService.listar();
    }

    @PostMapping(path = "/guardar", consumes = "application/json")
    public AdmiApi guardar(@RequestBody AdmiApi dato) throws Exception {
        log.info("Petición recibida: guardar");
        return apiService.guardar(dato);
    }

    @PostMapping(path = "/actualizar", consumes = "application/json")
    public AdmiApi actualizar(@RequestBody AdmiApi dato) throws Exception {
        log.info("Petición recibida: actualizar");
        return apiService.actualizar(dato);
    }
}
