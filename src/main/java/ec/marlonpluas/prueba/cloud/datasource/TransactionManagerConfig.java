package ec.marlonpluas.prueba.cloud.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuración transaction manager
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Configuration
public class TransactionManagerConfig {
    @Bean(name = "appTransactionManager")
    public ChainedTransactionManager telconetTransactionManager(@Qualifier("cloudTM") PlatformTransactionManager cloudTM) {
        return new ChainedTransactionManager(cloudTM);
    }
}
