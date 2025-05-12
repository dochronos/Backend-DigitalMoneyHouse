package com.example.activities_server.clients;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private static final String SECRET_TOKEN = "from-gateway";

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("X-Secret-Token", SECRET_TOKEN);
    }
}