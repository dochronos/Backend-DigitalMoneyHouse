package com.example.activities_server.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.activities_server.dto.CardDTO;

@FeignClient(name = "cards-server", url = "http://localhost:8086/api", configuration = FeignConfig.class)
public interface CardClient {

    @GetMapping("/accounts/{userId}/cards/by-number/{cardNumber}")
    CardDTO getCardByLastFourNumbers(@PathVariable("userId") Long userId, @PathVariable("cardNumber") String cardNumber);
}
