package Pismo.demo.dto.TransactionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "Transaction creation request payload")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransactionRequest {

    @Schema(description = "Account ID associated with the transaction", example = "1")
    @NotNull(message = "Account ID is required")
    private Long accountId;

    @Schema(description = "Operation type ID", example = "4")
    @NotNull(message = "Operation type ID is required")
    private Long operationTypeId;

    @Schema(description = "Transaction amount", example = "123.45")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;
}