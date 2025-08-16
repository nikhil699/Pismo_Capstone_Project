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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;

    public Optional<TransactionResponse> createTransaction(TransactionRequest request) {
        log.info("Creating transaction: {}", request);

        // 1. Fetch account
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> {
                    log.error("Account not found with ID: {}", request.getAccountId());
                    return new ResourceNotFoundException("Account not found with ID: " + request.getAccountId());
                });

        // 2. Fetch operation type
        OperationType operationType = operationTypeRepository.findById(request.getOperationType_id())
                .orElseThrow(() -> {
                    log.error("Operation type not found with ID: {}", request.getOperationType_id());
                    return new ResourceNotFoundException("Operation type not found with ID: " + request.getOperationType_id());
                });

        // 3. Apply business rules for amount
        BigDecimal adjustedAmount = adjustAmountByOperationType(request.getAmount(), operationType.getOperationTypeId());
        request.setAmount(adjustedAmount);

        log.debug("Adjusted amount for operation type {}: {}", operationType.getDescription(), adjustedAmount);

        // 4. Save transaction
        Transaction transaction = EntityMapper.toTransaction(account, operationType, request);
        Transaction savedTransaction = transactionRepository.save(transaction);

        log.info("Transaction created successfully: ID {}", savedTransaction.getTransactionId());

        return Optional.of(EntityMapper.toTransactionResponse(savedTransaction));
    }

    /**
     * Adjusts the transaction amount based on operation type rules
     */
    private BigDecimal adjustAmountByOperationType(BigDecimal amount, Long operationTypeId) {
        if (isNegativeOperation(operationTypeId)) {
            return amount.abs().negate(); // Make it negative
        }
        if (isPositiveOperation(operationTypeId)) {
            return amount.abs(); // Ensure positive
        }
        return amount; // Default (if no rule matched)
    }

    private boolean isNegativeOperation(Long operationTypeId) {
        return operationTypeId == 1 || operationTypeId == 2 || operationTypeId == 3;
    }

    private boolean isPositiveOperation(Long operationTypeId) {
        return operationTypeId == 4;
    }
}
