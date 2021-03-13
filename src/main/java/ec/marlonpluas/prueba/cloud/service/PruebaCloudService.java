package ec.marlonpluas.prueba.cloud.service;

import org.springframework.stereotype.Service;

import ec.marlonpluas.prueba.cloud.dto.PruebaCloudReqDTO;

@Service
public class PruebaCloudService {

	public String prueba(PruebaCloudReqDTO request) {
		return "Hello "+request.getTo()+" your message will be send";
	}
}
