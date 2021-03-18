package ec.marlonpluas.prueba.cloud.controller;

import ec.marlonpluas.prueba.cloud.entity.AdmiApi;
import ec.marlonpluas.prueba.cloud.service.ApiService;
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
    @Autowired
    private ApiService apiService;

    @GetMapping("/listar")
    public List<AdmiApi> listar() {
        return apiService.listar();
    }

    @PostMapping(path = "/guardar", consumes = "application/json")
    public AdmiApi guardar(@RequestBody AdmiApi dato) throws Exception {
        return apiService.guardar(dato);
    }

    @PostMapping(path = "/actualizar", consumes = "application/json")
    public AdmiApi actualizar(@RequestBody AdmiApi dato) throws Exception {
        return apiService.actualizar(dato);
    }
}
