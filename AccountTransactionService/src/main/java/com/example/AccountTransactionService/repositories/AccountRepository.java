package com.example.AccountTransactionService.repositories;

import com.example.AccountTransactionService.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.List;

@Repository
public class AccountRepository {

    private final DynamoDbTable<Account> accountTable;

    @Autowired
    private DynamoDbClient dynamoDbClient;

    public AccountRepository(DynamoDbTable<Account> accountTable){
        this.accountTable = accountTable;
    }

    public void save(Account account){
        accountTable.putItem(PutItemEnhancedRequest.builder(Account.class).item(account).build());
    }

    public void update(Account account){
        accountTable.updateItem(account);
    }

    public Account getById(String id){
        Key key = Key.builder().partitionValue(id).build();
        return accountTable.getItem(key);
    }


    public void deleteById(String accountId) {
        Key key = Key.builder().partitionValue(accountId).build();
        accountTable.deleteItem(DeleteItemEnhancedRequest.builder().key(key).build());
    }


}



