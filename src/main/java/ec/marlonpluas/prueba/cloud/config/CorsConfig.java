package ec.marlonpluas.prueba.cloud.config;

import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuración para habilitar las políticas CORS
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 02/03/2020
 */
@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> customCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(Collections.singletonList(CorsConfiguration.ALL));
        config.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
        config.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
        config.setMaxAge((long) 86400);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setName("CORS");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
