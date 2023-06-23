package com.example.AccountTransactionService.services;

import com.example.AccountTransactionService.models.Account;
import com.example.AccountTransactionService.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.update(account);
    }

    public Account getAccountById(String accountId) {
        return accountRepository.getById(accountId);
    }


    public void deleteAccountById(String accountId) {
        accountRepository.deleteById(accountId);
    }
}

