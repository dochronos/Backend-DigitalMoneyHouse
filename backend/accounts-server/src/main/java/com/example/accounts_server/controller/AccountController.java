package com.example.accounts_server.controller;

import com.example.accounts_server.dto.*;
import com.example.accounts_server.entities.Account;
import com.example.accounts_server.exceptions.ResourceNotFoundException;
import com.example.accounts_server.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<AccountCreatedDTO> createAccount(@Valid @RequestBody UserDTO userDTO) {
        Account accountCreated = accountService.createAccount(userDTO);
        AccountCreatedDTO responseDTO = new AccountCreatedDTO(accountCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/accounts/{userId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long userId) {
        try {
            AccountDTO accountDTO = accountService.getAccountByUserId(userId);
            return ResponseEntity.ok(accountDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/accounts/{userId}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long userId, @Valid @RequestBody UpdateDTO updateDTO) {
        try {
            AccountDTO accountUpdatedDTO = accountService.updateAccountByUserId(userId, updateDTO);
            return ResponseEntity.ok(accountUpdatedDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/accounts/{userId}/balance")
    public ResponseEntity<AccountDTO> updateAccountBalance(@PathVariable Long userId, @RequestBody Double amount) {
        try {
            Account updatedAccount = accountService.updateAccountBalance(userId, amount);
            return ResponseEntity.ok(new AccountDTO(updatedAccount));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/accounts/getAll")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        try {
            List<AccountDTO> accounts = accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/accounts/search")
    public ResponseEntity<AccountDTO> getAccountByCvuOrAlias(@RequestBody AccountSearchRequest request) {
        try {
            String cvu = request.getCvu();
            String alias = request.getAlias();

            if (cvu == null && alias == null) {
                return ResponseEntity.badRequest().build();
            }

            AccountDTO accountDTO = accountService.getAccountByCvuOrAlias(cvu, alias);
            return ResponseEntity.ok(accountDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
