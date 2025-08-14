package Pismo.demo.service;

import Pismo.demo.dto.TransactionDTO.TransactionRequest;
import Pismo.demo.dto.TransactionDTO.TransactionResponse;
import Pismo.demo.entity.Account;
import Pismo.demo.entity.OperationType;
import Pismo.demo.entity.Transaction;
import Pismo.demo.repository.AccountRepository;
import Pismo.demo.repository.OperationTypeRepository;
import Pismo.demo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction() {
        Account account = Account.builder()
                .accountId(1L)
                .documentNumber("12345678900")
                .build();

        OperationType opType = OperationType.builder()
                .operationTypeId(4L)
                .description("PAYMENT")
                .build();

        TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .operationTypeId(4L)
                .amount(new BigDecimal("200.00"))
                .build();

        Transaction savedTransaction = Transaction.builder()
                .transactionId(1L)
                .account(account)
                .operationType(opType)
                .amount(new BigDecimal("200.00"))
                .eventDate(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(4L)).thenReturn(Optional.of(opType));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        Optional<TransactionResponse> responseOpt = transactionService.createTransaction(request);

        assertThat(responseOpt).isPresent();
        assertThat(responseOpt.get().getAmount()).isEqualTo(new BigDecimal("200.00"));
        assertThat(responseOpt.get().getOperationTypeId()).isEqualTo(4L);
    }
}
