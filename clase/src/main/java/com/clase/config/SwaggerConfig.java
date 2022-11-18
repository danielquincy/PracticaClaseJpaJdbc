package com.clase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiINfo(){
        return new ApiInfoBuilder()
                .title("Titulo de mi API")
                .description("Esta es al descripción de la API de Daniel")
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiINfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.web"))
                .paths(PathSelectors.any())
                .build();
    }
}
