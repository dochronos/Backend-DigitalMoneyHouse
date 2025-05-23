package com.example.accounts_server.services;

import com.example.accounts_server.dto.AccountDTO;
import com.example.accounts_server.dto.UpdateDTO;
import com.example.accounts_server.dto.UserDTO;
import com.example.accounts_server.entities.Account;
import com.example.accounts_server.exceptions.ResourceNotFoundException;
import com.example.accounts_server.repositories.AccountRepository;
import com.example.accounts_server.utils.AccountUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountUtils accountUtils;


    public AccountService(AccountRepository accountRepository, AccountUtils accountUtils) {
        this.accountRepository = accountRepository;
        this.accountUtils = accountUtils;
    }

    public AccountDTO getAccountByUserId(Long userId) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for userId " + userId));
        return new AccountDTO(account);
    }

    public AccountDTO updateAccountByUserId(Long userId, UpdateDTO updateDTO) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for userId " + userId));

        account.update(updateDTO);

        Account updatedAccount = accountRepository.save(account);

        return new AccountDTO(updatedAccount);
    }

    public Account createAccount(UserDTO userDTO) {
        String cvu = accountUtils.generateRandomCVU();
        String alias = accountUtils.generateAlias();

        Account account = new Account(userDTO, cvu, alias);

        return accountRepository.save(account);
    }

    public Account updateAccountBalance(Long userId, Double amount) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for userId " + userId));

        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    public AccountDTO getAccountByCvuOrAlias(String cvu, String alias) {
        Account account = null;

        if (cvu != null) {
            account = accountRepository.findByCvu(cvu)
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found for CVU: " + cvu));
        } else if (alias != null) {
            account = accountRepository.findByAlias(alias)
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found for alias: " + alias));
        }

        return new AccountDTO(account);
    }
}