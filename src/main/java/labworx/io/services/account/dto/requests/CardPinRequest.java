package labworx.io.services.account.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardPinRequest {
    private Long accountId;
    private Long bankCardId;
    private String currentPin;
    private String newPin;
}
