package com.example.AccountTransactionService.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String Id;
    private String accountId;
    private double amount;
    private String type;
    private Date date;
}
