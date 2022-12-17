package labworx.io.services.account.dto.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Getter
@Setter
public class AuthenticationRequest {
    public String username;
    public String password;
}
