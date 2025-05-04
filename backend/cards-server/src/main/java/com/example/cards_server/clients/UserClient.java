package com.example.cards_server.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-server", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("api/users/exists/{id}")
    boolean userExists(@PathVariable("id") Long id);
}
