package Pismo.demo.Controller;


import Pismo.demo.dto.AccountDTO.AccountRequest;
import Pismo.demo.dto.AccountDTO.AccountResponse;
import Pismo.demo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static Pismo.demo.constants.ApiStatusCodes.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Create a new account", description = "Creates a new account with the provided document number")
    @ApiResponses({
            @ApiResponse(responseCode = CREATED, description = "Account created successfully"),
            @ApiResponse(responseCode = BAD_REQUEST, description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get account details", description = "Retrieves account details by account ID")
    @ApiResponses({
            @ApiResponse(responseCode = OK, description = "Account found"),
            @ApiResponse(responseCode = NOT_FOUND, description = "Account not found")
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId)
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
