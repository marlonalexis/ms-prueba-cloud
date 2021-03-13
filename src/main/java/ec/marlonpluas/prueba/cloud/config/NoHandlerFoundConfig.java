package ec.marlonpluas.prueba.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

/**
 * Configuración para habilitar la excepción NoHandlerFound
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 02/03/2020
 */
@EnableWebMvc
@Configuration
public class NoHandlerFoundConfig {
    @Autowired
    private DispatcherServlet dispatcherServlet;

    @PostConstruct
    private void configureDispatcherServlet() {
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}