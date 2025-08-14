package Pismo.demo.service;

import Pismo.demo.dto.AccountDTO.AccountRequest;
import Pismo.demo.dto.AccountDTO.AccountResponse;
import Pismo.demo.entity.Account;
import Pismo.demo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        AccountRequest request = AccountRequest.builder()
                .documentNumber("12345678900")
                .build();

        Account savedAccount = Account.builder()
                .accountId(1L)
                .documentNumber("12345678900")
                .build();

        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        AccountResponse response = accountService.createAccount(request);

        assertThat(response.getAccountId()).isEqualTo(1L);
        assertThat(response.getDocumentNumber()).isEqualTo("12345678900");
    }

    @Test
    void testGetAccount() {
        Account account = Account.builder()
                .accountId(1L)
                .documentNumber("12345678900")
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Optional<AccountResponse> responseOpt = accountService.getAccount(1L);

        assertThat(responseOpt).isPresent();
        assertThat(responseOpt.get().getAccountId()).isEqualTo(1L);
    }
}
