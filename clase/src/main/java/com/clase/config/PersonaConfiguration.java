package com.clase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersonaConfiguration {

    @Value("${usr}")
    private String usr;

    @Value("${pas}")
    private String pas;

    @Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:sqlserver://192.168.10.136;databaseName=PRUEBA;trustServerCertificate=true");
        dataSourceBuilder.username("UCEM_IRENE");
        dataSourceBuilder.password("1234");
        return dataSourceBuilder.build();
    }
}
