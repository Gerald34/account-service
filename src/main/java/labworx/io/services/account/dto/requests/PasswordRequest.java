package labworx.io.services.account.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {
    private Long id;
    private String email;
    private String currentPassword;
    private String newPassword;
    private String verification;
}
