package com.example.users_server.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.users_server.dto.AccountCreatedDTO;
import com.example.users_server.dto.AccountToCreateDTO;

@FeignClient(name = "accounts-server", url = "http://localhost:8085/api")
public interface AccountServiceClient {

    @PostMapping("/create-account")
    AccountCreatedDTO createAccount(@RequestBody AccountToCreateDTO accountToCreateDTO);
}
