package com.example.AccountTransactionService.services;

import com.example.AccountTransactionService.models.Transaction;
import com.example.AccountTransactionService.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }


}
