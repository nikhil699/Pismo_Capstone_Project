package Pismo.demo.service;

import Pismo.demo.dto.AccountDTO.AccountRequest;
import Pismo.demo.dto.AccountDTO.AccountResponse;
import Pismo.demo.dto.Mappers.EntityMapper;
import Pismo.demo.entity.Account;
import Pismo.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @CacheEvict(value = "accounts", allEntries = true) // Clear cache when a new account is created
    public AccountResponse createAccount(AccountRequest request) {
        System.out.println("🛠 Creating new account, clearing cache...");
        Account account = EntityMapper.toAccount(request);
        Account saved = accountRepository.save(account);
        return EntityMapper.toAccountResponse(saved);
    }

    @Cacheable(value = "accounts", key = "#accountId") // Cache by accountId
    public AccountResponse getAccount(Long accountId) {
        System.out.println("🔍 [DB CALL] Fetching accountId: " + accountId + " from Database");
        return accountRepository.findById(accountId)
                .map(EntityMapper::toAccountResponse)
                .orElse(null); // null will be cached as well
    }
}
