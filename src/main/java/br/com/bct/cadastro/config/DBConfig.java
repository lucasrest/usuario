package br.com.bct.cadastro.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    @Value("${db-config.url}")
    private String jdbcUrl;

    @Value("${db-config.username}")
    private String databaseUsername;

    @Value("${db-config.password}")
    private String databasePassword;

    @Value("${db-config.driver-class-name}")
    private String drive;

    @Value("${db-config.setMaxStatements}")
    private String setMaxStatements;

    @Value("${db-config.setMaxPoolSize}")
    private Integer setMaxPoolSize;

    @Value("${db-config.setMinPoolSize}")
    private Integer setMinPoolSize;

    @Value("${db-config.setIdleTimeOut}")
    private Integer setIdleTimeOut;

    @Value("${db-config.setConnectionTimeout}")
    private Integer setConnectionTimeout;

    @Value("${db-config.setMaxLifeTime}")
    private Integer setMaxLifeTime;

    @Bean
    @ConfigurationProperties(prefix="app.datasource")
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(databaseUsername);
        config.setPassword(databasePassword);
        config.setRegisterMbeans(true);
        config.setDriverClassName(drive);
        config.setMinimumIdle(setMinPoolSize);
        config.setMaximumPoolSize(setMaxPoolSize);
        config.setIdleTimeout(setIdleTimeOut);
        config.setConnectionTimeout(setConnectionTimeout);
        config.setMaxLifetime(setMaxLifeTime);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , setMaxStatements);
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);
        return ds;
    }

}
