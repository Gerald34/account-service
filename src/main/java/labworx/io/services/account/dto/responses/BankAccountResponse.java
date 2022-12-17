package labworx.io.services.account.dto.responses;

import labworx.io.services.account.dto.BankAccountDto;
import labworx.io.services.account.entities.Account;
import lombok.Data;

@Data
public class BankAccountResponse {
    private Long accountId;
    private BankAccountDto banking;

    public BankAccountResponse(Account account, BankAccountDto bankAccountDto) {
        this.accountId = account.getId();
        banking = bankAccountDto;
    }
}
