package labworx.io.services.account.services;

import labworx.io.services.account.components.JwtTokenUtil;
import labworx.io.services.account.dto.requests.AuthenticationRequest;
import labworx.io.services.account.dto.responses.AuthorizationResponse;
import labworx.io.services.account.entities.Account;
import labworx.io.services.account.exceptions.AuthorisationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service(value = "userService")
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private Account account = null;

    public AuthorizationResponse authenticate(AuthenticationRequest request) {
        account = accountService.getByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), account.getPassword()))
            throw new AuthorisationException("Not authorised, either username or password is incorrect.");

        String token = jwtTokenUtil.generateToken(account);

        return new AuthorizationResponse(account, token, "Bearer");
    }

    public UserDetails loadUserByUsername(String username) {
        return new User(account.getUsername(), account.getPassword(), account.getAuthorities());
    }

}
