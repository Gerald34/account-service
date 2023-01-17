package labworx.io.services.account.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import labworx.io.services.account.dto.requests.AccountRegistrationRequest;
import labworx.io.services.account.dto.requests.AccountRequest;
import labworx.io.services.account.dto.requests.AddressRequest;
import labworx.io.services.account.dto.requests.PasswordRequest;
import labworx.io.services.account.dto.responses.AccountResponse;
import labworx.io.services.account.dto.responses.GenericResponse;
import labworx.io.services.account.exceptions.NoContentException;
import labworx.io.services.account.services.AccountService;
import labworx.io.services.account.services.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;
import org.springframework.data.domain.Pageable;

import javax.annotation.security.RolesAllowed;

@RestController
@Slf4j
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final SecurityService securityService;

    @GetMapping("")
    @RolesAllowed({"SUPER_USER", "ADMIN"})
    public ResponseEntity<Page<AccountResponse>> getAccounts(Pageable pageable) {
        return ok(accountService.getAccounts(pageable));
    }

    @PostMapping("create")
    public ResponseEntity<AccountResponse> signup(@RequestBody AccountRegistrationRequest accountRegistrationRequest) throws JsonProcessingException {
        log.info("Account registration {}", accountRegistrationRequest.getEmail());
        return ok(accountService.createAccount(accountRegistrationRequest));
    }

    @PutMapping("update")
    public ResponseEntity<AccountResponse> updateAccountDetails(@RequestBody AccountRequest accountRequest) throws NoContentException {
        return ok(accountService.updateAccount(accountRequest));
    }

    @DeleteMapping("deactivate/{id}")
    public ResponseEntity<Boolean> deactivateAccount(@PathVariable("id") Integer id) {
        return ok(true);
    }

    @PostMapping("add-address")
    public ResponseEntity<GenericResponse> addAddress(@RequestBody AddressRequest addressRequest) throws NoContentException {
        accountService.addAddress(addressRequest);
        return ok(new GenericResponse(false, "Address has been updated."));
    }

    @PostMapping("verify")
    public ResponseEntity<Boolean> verify(PasswordRequest request)
            throws NoContentException {
        return ok(securityService.resetVerification(request));
    }

    @PostMapping("reset-password")
    public ResponseEntity<GenericResponse> reset(PasswordRequest request)
            throws NoContentException {
        return ok(securityService.resetPassword(request));
    }
}
