package com.spring.sample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.YearMonth

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .directModelSubstitute(YearMonth::class.java, String::class.java)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/api/**"))
            .build()
    }
}