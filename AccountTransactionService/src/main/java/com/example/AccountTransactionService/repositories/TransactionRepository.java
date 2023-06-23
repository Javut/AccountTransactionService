package com.example.AccountTransactionService.repositories;

import com.example.AccountTransactionService.models.Transaction;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;


public class TransactionRepository {
    private final DynamoDbTable<Transaction> transactionTable;

    public TransactionRepository(DynamoDbEnhancedClient enhancedClient) {
        DynamoDbTable<Transaction> table = enhancedClient.table("your-transaction-table-name", TableSchema.fromBean(Transaction.class));
        this.transactionTable = table;
    }

    public void save(Transaction transaction) {
        try {
            PutItemEnhancedRequest<Transaction> request = PutItemEnhancedRequest.builder(Transaction.class)
                    .item(transaction)
                    .build();
            transactionTable.putItem(request);
        } catch (DynamoDbException e) {
            // Manejar la excepción
        }
    }



}
