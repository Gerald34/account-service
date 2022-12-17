package labworx.io.services.account.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    private Long accountId;
    private String name;
    private Boolean primary;
    private String accountNumber;
    private String cardNumber;
    private String expiryDate;
}
