package com.example.auth_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Punto de entrada principal para el microservicio de autenticación.
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.auth_server.clients")
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}