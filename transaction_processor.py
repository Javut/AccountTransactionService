import boto3 #Empleada para AWS
from pymongo import MongoClient

#Configuracion de la conexión con MongoDB
client = MongoClient("<MongoDB_URL>")
db = client["database_name"]
collection = db["transactions"]

def process_transaction(transaction_data);

# Obteniendo los datos de la transacción
account_id = transaction_data['accountId']
amount = transaction_data['amount']
transaction_type = transaction_data['type']

# Transferencia entre cuentas bancarias
if transaction_type == "transfer":
    source_account = collection.find_one({"accountId": account_id})
    destination_acount = collection.find_one({"accountId": transaction_data['destinationAccountId']})

        # Se verifica si hay suficientes fondos en la cuenta origen para realizar la transferencia
    if source_account['balance'] >= amount:
        new_source_balance = source_account['balance'] - amount
        new_destination_balance = destination_acount['balance'] + amount

        # Se Actualizan los saldos en la base de datos
        collection.update_one({"accountId": account_id}, {"$set": {"balance": new_source_balance}})
        collection.update_one({"accountId": transaction_data['destinationAccountId']}, {"$set": {"balance": new_destination_balance}})

        print("Transferencia Exitosa")

    else:
        print("Transferencia no compatible")

        print("Procesando transacción");
        print(transaction_data);


transaction_data = {
    "accountId": "account_id_1",
    "amount": 100,
    "type": "transfer",
    "destinationAccountId": "account_id_2"
}

process_transaction(transaction_data)
