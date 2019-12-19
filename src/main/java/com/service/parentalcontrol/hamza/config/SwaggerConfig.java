package com.service.parentalcontrol.hamza.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket movieServiceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.service.parentalcontrol.hamza"))
                .paths(regex("/dynamoDb.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Movie Service API",
                "REST API documentation for Movie Service API",
                "1.0",
                "Terms of Service",
                new Contact("Hamza Busuri", "https://www.linkedin.com/in/hamza-busuri/",
                        "hamza.busuri@and.digital"),
                "Apache License Version 2.0",
                "http://apache.org/licenses/LICENSE-2.0.txt"
        );

        return apiInfo;
    }
}