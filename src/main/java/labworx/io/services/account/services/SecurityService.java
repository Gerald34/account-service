package labworx.io.services.account.services;

import labworx.io.services.account.dto.requests.PasswordRequest;
import labworx.io.services.account.dto.responses.GenericResponse;
import labworx.io.services.account.entities.PasswordVerification;
import labworx.io.services.account.exceptions.NoContentException;
import labworx.io.services.account.exceptions.ResourceException;
import labworx.io.services.account.repositories.AccountRepository;
import labworx.io.services.account.repositories.PasswordVerificationRepository;
import labworx.io.services.account.utils.RandomStringGenerator;
import labworx.io.services.account.utils.StringEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final PasswordVerificationRepository verificationRepository;
    private final AccountRepository _accountRepository;
    private final AccountService accountService;

    /**
     * Renew account password
     * @param email String
     * @return GenericResponse
     * @throws NoContentException exception
     */
    public GenericResponse renewPassword(String email) throws NoContentException {
        var account = _accountRepository.findAllByEmail(email);

        if (account == null)
            throw new NoContentException("Account does not exist");

        if (account.getActive())
            throw new ResourceException(true, "Account not activated");

        var token = RandomStringGenerator.generate(6);
        var reset = new PasswordVerification(account, token);
        verificationRepository.save(reset);

        // Todo
        // Email token to account owner

        return new GenericResponse(false, "Verification code sent to " + email);
    }

    /**
     * Verify token
     * @param request PasswordRequest
     * @return GenericResponse
     * @throws NoContentException exception
     */
    public Boolean resetVerification(PasswordRequest request) throws NoContentException {
        var verification = verificationRepository.findByAccount(accountService.getUserById(request.getId()));

        if (verification == null || !Objects.equals(request.getVerification(), verification.getVerification()))
            throw new NoContentException("Not authorised to change password");

        verificationRepository.deleteByVerification(request.getVerification());
        return true;
    }

    /**
     * Reset password
     * @param request GenericResponse
     * @return GenericResponse
     * @throws NoContentException exception
     */
    public GenericResponse resetPassword(PasswordRequest request) throws NoContentException {
        var account = accountService.getUserById(request.getId());
        account.setPassword(StringEncryptor.Encrypt(request.getNewPassword()));
        _accountRepository.save(account);
        return new GenericResponse(false, "Password accepted");
    }
}
