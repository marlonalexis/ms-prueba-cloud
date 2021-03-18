package ec.marlonpluas.prueba.cloud.service;

import ec.marlonpluas.prueba.cloud.entity.AdmiApi;
import ec.marlonpluas.prueba.cloud.enums.Estado;
import ec.marlonpluas.prueba.cloud.repository.AdmiApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApiService {
    @Autowired
    private AdmiApiRepository admiApiRepo;

    public List<AdmiApi> listar() {
        return admiApiRepo.findAll();
    }

    @Transactional(rollbackFor = { Exception.class }, value = "appTransactionManager")
    public AdmiApi guardar(AdmiApi request) throws Exception {
        AdmiApi apiNuevo = new AdmiApi();
        if (request.getValor() == null) {
            throw new Exception("valor es requerido");
        }
        if (admiApiRepo.existsByValor(request.getValor())) {
            throw new Exception("valor ya existe");
        }
        if (request.getEstado() == null) {
            apiNuevo.setEstado(Estado.Activo.toString());
        }
        apiNuevo.setValor(request.getValor());
        return admiApiRepo.save(apiNuevo);
    }

    @Transactional(rollbackFor = { Exception.class }, value = "appTransactionManager")
    public AdmiApi actualizar(AdmiApi request) throws Exception {
        AdmiApi apiActualizar;
        if (request.getIdApi() == null) {
            throw new Exception("idApi es requerido");
        }
        Optional<AdmiApi> opAdmiApi = admiApiRepo.findById(request.getIdApi());
        if (opAdmiApi.isPresent()) {
            apiActualizar = opAdmiApi.get();
            apiActualizar.setValor(request.getValor() != null ? request.getValor() : apiActualizar.getValor());
            apiActualizar.setEstado(request.getEstado() != null ? request.getEstado() : apiActualizar.getEstado());
        } else {
            throw new Exception("idApi no existe");
        }
        return admiApiRepo.save(apiActualizar);
    }
}
