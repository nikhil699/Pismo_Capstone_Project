package Pismo.demo.service;

import Pismo.demo.dto.AccountDTO.AccountRequest;
import Pismo.demo.dto.AccountDTO.AccountResponse;
import Pismo.demo.dto.Mappers.EntityMapper;
import Pismo.demo.entity.Account;
import Pismo.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponse createAccount(AccountRequest request) {
        Account account = EntityMapper.toAccount(request);
        Account saved = accountRepository.save(account);
        return EntityMapper.toAccountResponse(saved);
    }

    public Optional<AccountResponse> getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .map(EntityMapper::toAccountResponse);
    }
}
