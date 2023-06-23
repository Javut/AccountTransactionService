package com.example.AccountTransactionService.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "account")
public class Account {

    @Id
    private String id;
    private String accountNumber;
    private String accountNamePerson;
    private double balance;
    private Date openingDate;


}
