package ec.marlonpluas.prueba.cloud.util.exception.controller;

import ec.marlonpluas.prueba.cloud.util.CoreUtilConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase genérica que orquesta las excepciones
 * 
 * @author Marlon Plúas
 * @version 1.0
 * @since 02/03/2020
 */
@ControllerAdvice
@RestController
public class ExceptionController {
	Logger log = LogManager.getLogger(this.getClass());
	
	/**
	 * Método que agrupa las excepciones
	 * 
	 * @author Marlon Plúas
	 * @version 1.0
	 * @since 02/03/2020
	 * 
	 * @param e Excepción
	 * @return String
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		log.error("Error generado: " + e.getMessage());
		return new ResponseEntity<>(CoreUtilConstants.MENSAJE_ERROR, HttpStatus.BAD_REQUEST);
	}
}
