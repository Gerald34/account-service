package labworx.io.services.account.dto.requests;

import org.springframework.web.context.annotation.RequestScope;

@RequestScope
public record AuthenticationRequest (String email, String password) {}
