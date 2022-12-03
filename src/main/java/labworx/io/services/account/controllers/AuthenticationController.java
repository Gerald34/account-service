package labworx.io.services.account.controllers;

import labworx.io.services.account.dto.requests.AuthenticationRequest;
import labworx.io.services.account.exceptions.BadRequestException;
import labworx.io.services.account.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("")
    public ResponseEntity<String> authenticate(AuthenticationRequest request) throws BadRequestException {
        return ok(authenticationService.authenticate(request));
    }
}
