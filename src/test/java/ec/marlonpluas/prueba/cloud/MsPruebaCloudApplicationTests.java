package ec.marlonpluas.prueba.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ec.marlonpluas.prueba.cloud.dto.PruebaCloudReqDTO;
import ec.marlonpluas.prueba.cloud.service.PruebaCloudService;

@SpringBootTest
class MsPruebaCloudApplicationTests {

	@Autowired
	PruebaCloudService pruebaCloudService;
	
	@Test
	void contextLoads() {
		PruebaCloudReqDTO requestTest = new PruebaCloudReqDTO();
		requestTest.setMessage("This is a test");
		requestTest.setTo("Juan Perez");
		requestTest.setFrom("Rita Asturia");
		requestTest.setTimeToLifeSec(45);
		
		System.out.println("Prueba de test: " + pruebaCloudService.prueba(requestTest));
	}

}
