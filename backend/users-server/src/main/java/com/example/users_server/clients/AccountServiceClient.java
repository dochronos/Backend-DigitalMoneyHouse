package com.example.users_server.clients;

import com.example.users_server.dto.AccountCreatedDTO;
import com.example.users_server.dto.AccountToCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "accounts-server",
    url = "${services.accounts-server.url}", // Se externaliza la URL
    path = "/api"
)
public interface AccountServiceClient {

    @PostMapping("/create-account")
    AccountCreatedDTO createAccount(@RequestBody AccountToCreateDTO accountToCreateDTO);
}
