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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "inmedssEMFactory", transactionManagerRef = "inmedssTM", basePackages = {
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

    // INICIO CONFIGURACIÓN INMEDSS
    @Primary
    @Bean(name = "dsInmedss")
    public HikariDataSource dsInmedss(@Qualifier("dsInmedssProperties") HikariConfig dataSourceConfig) {
        return new HikariDataSource(dataSourceConfig);
    }

    @Primary
    @Bean(name = "dsInmedssProperties")
    @ConfigurationProperties("inmedss.datasource")
    public HikariConfig dsInmedssConfig() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setPoolName("dsInmedss");
        dataSourceConfig.setConnectionTimeout(connectionTimeout);
        dataSourceConfig.setIdleTimeout(idleTimeout);
        dataSourceConfig.setMaximumPoolSize(maxPoolSize);
        dataSourceConfig.setMinimumIdle(minPoolSize);
        dataSourceConfig.setMaxLifetime(maxLifetime);
        dataSourceConfig.setValidationTimeout(10000);
        return dataSourceConfig;
    }

    @Primary
    @Bean(name = "jdbcInmedss")
    @Autowired
    public JdbcTemplate jdbcInmedssTemplate(@Qualifier("dsInmedss") DataSource dsInmedss) {
        return new JdbcTemplate(dsInmedss);
    }

    @Primary
    @Bean(name = "inmedssEMFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryInmedss(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("dsInmedss") DataSource dsInmedss) {
        return builder.dataSource(dsInmedss).properties(hibernateProperties()).packages("ec.marlonpluas.prueba.cloud.entity")
                .persistenceUnit("dbInmedss").build();
    }

    @Primary
    @Bean(name = "inmedssTM")
    public PlatformTransactionManager transactionManagerInmedss(@Qualifier("inmedssEMFactory") EntityManagerFactory inmedssEMFactory) {
        return new JpaTransactionManager(inmedssEMFactory);
    }
    // FIN CONFIGURACIÓN INMEDSS
}
