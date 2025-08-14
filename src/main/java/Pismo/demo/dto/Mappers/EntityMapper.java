package Pismo.demo.dto.Mappers;

import Pismo.demo.dto.AccountDTO.AccountRequest;
import Pismo.demo.dto.AccountDTO.AccountResponse;
import Pismo.demo.dto.TransactionDTO.TransactionRequest;
import Pismo.demo.dto.TransactionDTO.TransactionResponse;
import Pismo.demo.entity.Account;
import Pismo.demo.entity.OperationType;
import Pismo.demo.entity.Transaction;

import java.time.LocalDateTime;

public class EntityMapper {

    public static Account toAccount(AccountRequest request) {
        return Account.builder()
                .documentNumber(request.getDocumentNumber())
                .build();
    }

    public static AccountResponse toAccountResponse(Account account) {
        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .documentNumber(account.getDocumentNumber())
                .build();
    }

    public static Transaction toTransaction(Account account, OperationType operationType, TransactionRequest request) {
        return Transaction.builder()
                .account(account)
                .operationType(operationType)
                .amount(request.getAmount())
                .eventDate(LocalDateTime.now())
                .build();
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .accountId(transaction.getAccount().getAccountId())
                .operationTypeId(transaction.getOperationType().getOperationTypeId())
                .amount(transaction.getAmount())
                .eventDate(transaction.getEventDate())
                .build();
    }
}
