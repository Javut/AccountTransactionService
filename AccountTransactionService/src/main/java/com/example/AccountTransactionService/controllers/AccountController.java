package com.example.AccountTransactionService.controllers;

import com.example.AccountTransactionService.models.Account;
import com.example.AccountTransactionService.models.Transaction;
import com.example.AccountTransactionService.services.AccountService;
import com.example.AccountTransactionService.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{accountId}/deposits")
    public ResponseEntity<String> bankDeposit(@PathVariable("accountId") String accountId, @RequestBody Transaction depositTransaction){
        try{
            // Obtener la cuenta bancaria correspondiente al accountId
            Account account = accountService.getAccountById(accountId);

            // Realizar el depósito actualizando el balance de la cuenta
            double newBalance = account.getBalance() + depositTransaction.getAmount();
            account.setBalance(newBalance);

            // Utilizar una transacción para garantizar la consistencia de los datos
            // accountService.updateAccount realiza una operación de actualización atómica
            accountService.updateAccount(account);

            // Crear y guardar la transacción de depósito
            depositTransaction.setAccountId(accountId);
            depositTransaction.setType("deposit");
            depositTransaction.setDate(new Date());
            transactionService.saveTransaction(depositTransaction);

            return ResponseEntity.ok("Deposito Exitoso");



        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el deposito");
        }
    }

    @PostMapping("/{accountId}/withdrawals")
    public ResponseEntity<String> bankWithdrawal(@PathVariable("accountId") String accountId, @RequestBody Transaction withdrawalTransaction){
        try{
            // Obtener la cuenta bancaria correspondiente al accountId
            Account account = accountService.getAccountById(accountId);

            double withdrawalAmount = withdrawalTransaction.getAmount();
            double currentBalance = account.getBalance();
            // Verificar si hay suficientes fondos disponibles para realizar el retiro
            if (withdrawalAmount > currentBalance){
                return ResponseEntity.badRequest().body("Fondos insuficientes");
            }


            // Realizar el retiro actualizando el balance de la cuenta
            double newBalance = currentBalance - withdrawalAmount;
            account.setBalance(newBalance);
            accountService.updateAccount(account);

            // Crear y guardar la transacción de retiro
            withdrawalTransaction.setAccountId(accountId);
            withdrawalTransaction.setType("withdrawal");
            withdrawalTransaction.setDate(new Date());
            transactionService.saveTransaction(withdrawalTransaction);

            return ResponseEntity.ok("Retiro Exitoso");


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el retiro");
        }
    }

}
