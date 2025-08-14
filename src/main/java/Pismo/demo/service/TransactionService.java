package Pismo.demo.service;

import Pismo.demo.dto.Mappers.EntityMapper;
import Pismo.demo.dto.TransactionDTO.TransactionRequest;
import Pismo.demo.dto.TransactionDTO.TransactionResponse;
import Pismo.demo.entity.Account;
import Pismo.demo.entity.OperationType;
import Pismo.demo.entity.Transaction;
import Pismo.demo.exception.CustomException.ResourceNotFoundException;
import Pismo.demo.repository.AccountRepository;
import Pismo.demo.repository.OperationTypeRepository;
import Pismo.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;

    public Optional<TransactionResponse> createTransaction(TransactionRequest request) {
        // Fetch account or throw exception
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // Fetch operation type or throw exception
        OperationType operationType = operationTypeRepository.findById(request.getOperationTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Operation type not found"));

        // Apply business rules
        BigDecimal amount = request.getAmount();
        if (isNegativeOperation(operationType.getOperationTypeId())) {
            amount = amount.negate();
        } else if (isPositiveOperation(operationType.getOperationTypeId())) {
            amount = amount.abs();
        }

        request.setAmount(amount);

        // Save transaction
        Transaction transaction = EntityMapper.toTransaction(account, operationType, request);
        Transaction saved = transactionRepository.save(transaction);

        return Optional.of(EntityMapper.toTransactionResponse(saved));
    }

    private boolean isNegativeOperation(Long operationTypeId) {
        return operationTypeId == 1 || operationTypeId == 2 || operationTypeId == 3;
    }

    private boolean isPositiveOperation(Long operationTypeId) {
        return operationTypeId == 4;
    }
}
