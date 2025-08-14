package Pismo.demo.dto.TransactionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Transaction details response")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransactionResponse {

    @Schema(description = "Transaction ID", example = "1")
    private Long transactionId;

    @Schema(description = "Account ID", example = "1")
    private Long accountId;

    @Schema(description = "Operation type ID", example = "4")
    private Long operationTypeId;

    @Schema(description = "Transaction amount", example = "123.45")
    private BigDecimal amount;

    @Schema(description = "Event date and time of the transaction")
    private LocalDateTime eventDate;
}