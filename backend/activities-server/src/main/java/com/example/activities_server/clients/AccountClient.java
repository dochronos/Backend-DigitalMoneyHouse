package com.example.activities_server.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.activities_server.dto.AccountDTO;
import com.example.activities_server.dto.AccountSearchRequest;
import com.example.activities_server.dto.TransferRequest;

@FeignClient(name = "accounts-server", url = "http://localhost:8085/api", configuration = FeignConfig.class)
public interface AccountClient {

    @PutMapping("/accounts/{userId}/balance")
    AccountDTO updateAccountBalance(@PathVariable("userId") Long userId, @RequestBody Double amount);

    @GetMapping("/accounts/{userId}")
    AccountDTO getAccountByUserId(@PathVariable("userId") Long userId);

    @PostMapping("/accounts/transfer")
    ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest);

    @PostMapping("/accounts/search")
    ResponseEntity<AccountDTO> getAccountByCvuOrAlias(@RequestBody AccountSearchRequest request);
}
