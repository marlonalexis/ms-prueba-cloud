package ec.marlonpluas.prueba.cloud.datasource;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuración datasource mysql
 *
 * @author Marlon Plúas
 * @version 1.0.0
 * @since 15/03/2021
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "cloudEMFactory", transactionManagerRef = "cloudTM", basePackages = {
        "ec.marlonpluas.prueba.cloud.repository" })
@EntityScan(basePackages = { "ec.marlonpluas.prueba.cloud.entity" })
public class DbMySqlConfig {
    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maxPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minPoolSize;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    private Map<String, Object> hibernateProperties() {
        Map<String, Object> hibernateProperties = new LinkedHashMap<>();
        hibernateProperties.put("hibernate.connection.release_mode", "auto");
        return hibernateProperties;
    }

    // INICIO CONFIGURACIÓN CLOUD
    @Primary
    @Bean(name = "dsCloud")
    public HikariDataSource dsCloud(@Qualifier("dsCloudProperties") HikariConfig dataSourceConfig) {
        return new HikariDataSource(dataSourceConfig);
    }

    @Primary
    @Bean(name = "dsCloudProperties")
    @ConfigurationProperties("cloud.datasource")
    public HikariConfig dsCloudConfig() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setPoolName("dsCloud");
        dataSourceConfig.setConnectionTimeout(connectionTimeout);
        dataSourceConfig.setIdleTimeout(idleTimeout);
        dataSourceConfig.setMaximumPoolSize(maxPoolSize);
        dataSourceConfig.setMinimumIdle(minPoolSize);
        dataSourceConfig.setMaxLifetime(maxLifetime);
        dataSourceConfig.setValidationTimeout(10000);
        return dataSourceConfig;
    }

    @Primary
    @Bean(name = "jdbcCloud")
    @Autowired
    public JdbcTemplate jdbcCloudTemplate(@Qualifier("dsCloud") DataSource dsCloud) {
        return new JdbcTemplate(dsCloud);
    }

    @Primary
    @Bean(name = "cloudEMFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryCloud(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("dsCloud") DataSource dsCloud) {
        return builder.dataSource(dsCloud).properties(hibernateProperties()).packages("ec.marlonpluas.prueba.cloud.entity")
                .persistenceUnit("dbCloud").build();
    }

    @Primary
    @Bean(name = "cloudTM")
    public PlatformTransactionManager transactionManagerCloud(@Qualifier("cloudEMFactory") EntityManagerFactory cloudEMFactory) {
        return new JpaTransactionManager(cloudEMFactory);
    }
    // FIN CONFIGURACIÓN CLOUD
}
