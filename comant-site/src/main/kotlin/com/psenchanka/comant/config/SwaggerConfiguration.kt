package com.psenchanka.comant.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.ApiKeyVehicle
import springfox.documentation.swagger.web.SecurityConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
open class SwaggerConfiguration {

    @Bean
    open fun api()
            = Docket(DocumentationType.SWAGGER_2)
            .select()
                .apis(RequestHandlerSelectors.basePackage("com.psenchanka.comant.controller"))
                .paths(PathSelectors.any())
                .build()
            .produces(setOf("application/json"))
            .securitySchemes(listOf(securitySchema()))
            .securityContexts(listOf(securityContext()))
            .apiInfo(ApiInfo("comant API", "", "1.0", "", Contact("", "", ""), "", ""))
            .useDefaultResponseMessages(false)
            .directModelSubstitute(java.time.LocalDate::class.java, java.util.Date::class.java)
            .directModelSubstitute(java.time.LocalDateTime::class.java, java.util.Date::class.java)

    //Security in Swagger UI doesn't work yet.
    @Bean
    open fun security() = SecurityConfiguration(
            null, null, null, null, null, ApiKeyVehicle.HEADER, "Authorization", ",")

    private fun securitySchema() = ApiKey("jwtKey", "api_key", "header")

    private fun securityContext()
            = SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build()

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScopes = arrayOf(AuthorizationScope("global", "accessEverything"))
        return listOf(SecurityReference("api_key", authorizationScopes))
    }
}
