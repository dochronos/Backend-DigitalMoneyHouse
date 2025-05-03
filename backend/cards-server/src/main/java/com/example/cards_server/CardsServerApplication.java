package com.example.cards_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.cards_server.clients")
public class CardsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsServerApplication.class, args);
    }
}
