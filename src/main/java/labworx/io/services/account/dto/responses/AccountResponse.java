package labworx.io.services.account.dto.responses;

import labworx.io.services.account.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponse {
    private String firstName;
    private String lastName;
    private String email;

    public AccountResponse(Account account) {
        firstName = account.getFirstName();
        lastName = account.getLastName();
        email = account.getEmail();
    }
}
