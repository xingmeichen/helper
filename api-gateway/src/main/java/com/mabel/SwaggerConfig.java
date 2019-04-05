package com.mabel;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-04-05 10:43
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public SecurityConfiguration securityConfig() {
        return new SecurityConfiguration(null, null, null, (String)null, null, ApiKeyVehicle.HEADER, "x-auth-token", "");
    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mabel.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("helper api")
                .description("This is helper api")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = Lists.newArrayList();
        apiKeys.add(new ApiKey("x-auth-token", "x-auth-token", "header"));
        return apiKeys;
    }
}