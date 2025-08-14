package Pismo.demo.dto.AccountDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(description = "Account creation request payload")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountRequest {

    @Schema(description = "Document number of the account holder", example = "12345678900")
    @NotBlank(message = "Document number is required")
    private String documentNumber;
}
