package ec.marlonpluas.prueba.cloud.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {
    @Bean(name = "appTransactionManager")
    public ChainedTransactionManager telconetTransactionManager(@Qualifier("inmedssTM") PlatformTransactionManager inmedssTM) {
        return new ChainedTransactionManager(inmedssTM);
    }
}
