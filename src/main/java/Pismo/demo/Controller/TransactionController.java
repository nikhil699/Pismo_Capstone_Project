package Pismo.demo.Controller;

import Pismo.demo.dto.TransactionDTO.TransactionRequest;
import Pismo.demo.dto.TransactionDTO.TransactionResponse;
import Pismo.demo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Create a transaction", description = "Creates a new transaction linked to an account and operation type")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transaction created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Account or Operation type not found")
    })
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request)
                .map(response -> ResponseEntity.status(201).body(response))
                .orElse(ResponseEntity.notFound().build());
    }
}