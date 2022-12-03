package labworx.io.services.account.services;

import labworx.io.services.account.dto.requests.AuthenticationRequest;
import labworx.io.services.account.exceptions.BadRequestException;
import labworx.io.services.account.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtGeneratorImplementation jwtGeneratorImplementation;
    private final AccountRepository accountRepository;

    public String authenticate(AuthenticationRequest request) throws BadRequestException {
        var account = accountRepository.findByEmail(request.email());

        if (!Objects.equals(account.getPassword(), request.password()))
            throw new BadRequestException("Not authorised");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword()));

        return jwtGeneratorImplementation.generateToken(account).toString();
    }
}
