package com.spring.sample.config

import java.time.YearMonth
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.BasicAuth
import springfox.documentation.service.SecurityReference
import springfox.documentation.service.SecurityScheme
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration

@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun swagger(): Docket {

        SecurityReference
            .builder()
            .reference("")
            .scopes(arrayOf(AuthorizationScope("asd", "asd")))
            .build()

        return Docket(DocumentationType.SWAGGER_2)
            .directModelSubstitute(YearMonth::class.java, String::class.java)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/api/**"))
            .build()
    }

    private fun basicAuthScheme(): SecurityScheme? {
        return BasicAuth("basicAuth")
    }

    private fun basicAuthReference(): SecurityReference? {
        return SecurityReference("basicAuth", arrayOfNulls(0))
    }
}