package labworx.io.services.account.dto.requests;

import labworx.io.services.account.entities.BankAccount;
import labworx.io.services.account.entities.BankAccount.Type;
import lombok.Data;

@Data
public class BankAccountRequest {
    private Long accountId;
    private Type type;
    private Boolean active;

    public BankAccountRequest(BankAccount.Type type, Boolean active) {
        this.type = type;
        this.active = active;
    }
}
