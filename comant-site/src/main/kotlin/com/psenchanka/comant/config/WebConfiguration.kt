package com.psenchanka.comant.config

import com.psenchanka.comant.auth.AuthenticationInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
open class WebConfiguration : WebMvcConfigurerAdapter() {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**")
    }

    @Bean
    open fun authenticationInterceptor() = AuthenticationInterceptor()
}
