package Pismo.demo.dto.AccountDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Account details response")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountResponse {

    @Schema(description = "Account ID", example = "1")
    private Long accountId;

    @Schema(description = "Document number of the account holder", example = "12345678900")
    private String documentNumber;
}