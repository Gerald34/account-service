package labworx.io.services.account.dto.requests;

import labworx.io.services.account.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String primaryContactNumber;
    private String identityNumber;
    private Roles role;
}
