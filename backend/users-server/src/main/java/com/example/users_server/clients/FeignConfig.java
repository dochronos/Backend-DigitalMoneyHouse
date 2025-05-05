package com.example.users_server.clients;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private static final String SECRET_TOKEN = "from-gateway";
    private static final String HEADER_NAME = "X-Secret-Token";

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header(HEADER_NAME, SECRET_TOKEN);
    }
}
