package labworx.io.services.account.dto.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateRequest {
    public String first_name;
    public String last_name;
    public String email;
    public String password;
}
