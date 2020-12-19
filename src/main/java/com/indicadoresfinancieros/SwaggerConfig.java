package com.indicadoresfinancieros;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.indicadoresfinancieros"))
                .paths(regex("/bideafactory/.*"))
                .build();
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "OTTAPI",
                "Indicadores Financieros API",
                "1.0.0",
                "Terms of Service",
                new Contact("Gabriel", "https://www.github.com/GabrielJacobina",
                        "sgabrieljacobina@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}