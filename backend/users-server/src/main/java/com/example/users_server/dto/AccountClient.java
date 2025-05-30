package com.example.users_server.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "accounts-server", path = "/api/accounts")
public interface AccountClient {

    @PostMapping("/create")
    AccountCreatedDTO createAccount(@RequestParam("userId") Long userId);
}
