package com.agr.workoutscheduledtelegrambot.db;

import com.agr.workoutscheduledtelegrambot.db.meta.WSMeta;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean("flyway")
    public Flyway flyway() {
        FluentConfiguration fluentConfiguration = Flyway.configure()
                .dataSource(dbUrl, dbUser, dbPassword)
                .schemas(WSMeta.schema);
        Flyway flyway = fluentConfiguration.load();
        flyway.migrate();
        return flyway;
    }

    @DependsOn("flyway")
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        return new HikariDataSource(config);
    }
}
