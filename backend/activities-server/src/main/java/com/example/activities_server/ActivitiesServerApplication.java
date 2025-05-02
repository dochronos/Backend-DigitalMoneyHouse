package com.example.activities_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ActivitiesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiesServerApplication.class, args);
	}
}