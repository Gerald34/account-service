package labworx.io.services.account.services;

import labworx.io.services.account.dto.requests.AccountRegistrationRequest;
import labworx.io.services.account.dto.requests.AccountRequest;
import labworx.io.services.account.dto.requests.AddressRequest;
import labworx.io.services.account.dto.responses.AccountResponse;
import labworx.io.services.account.entities.Account;
import labworx.io.services.account.entities.UserAddress;
import labworx.io.services.account.exceptions.NoContentException;
import labworx.io.services.account.exceptions.ResourceException;
import labworx.io.services.account.interfaces.JwtGeneratorInterface;
import labworx.io.services.account.repositories.AccountRepository;
import labworx.io.services.account.repositories.UserAddressRepository;
import labworx.io.services.account.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository _accountRepository;
    private final UserAddressRepository userAddressRepository;
    private final JwtGeneratorInterface jwtGenerator;

    /**
     * Fetch accounts
     * @param pageable Pageable
     * @return Pageable AccountResponse
     */
    public Page<AccountResponse> getAccounts(Pageable pageable) {
        return _accountRepository.findAll(pageable).map(AccountResponse::new);
    }

    /**
     * Create new account
     * @param request AccountRegistrationRequest
     * @return AccountResponse
     */
    public AccountResponse createAccount(AccountRegistrationRequest request) {
        // Check if email is valid
        if (!ValidationUtil.isEmailValid(request.getEmail()))
            throw new ResourceException(true, "Email not valid");
        // Check if email exists
        if(verifyEmail(request.getEmail()))
            throw new ResourceException(true, "Email already taken");

        var account = new Account(request);
        account.setRole(request.getRole());
        log.info("saving account");
        _accountRepository.save(account);
        return new AccountResponse(account);
    }

    private Boolean verifyEmail(String email) {
        return _accountRepository.findAllByEmail(email) != null;
    }

    /**
     * Update user account
     * @param request AccountResponse
     * @return AccountResponse
     * @throws NoContentException exception
     */
    public AccountResponse updateAccount(AccountRequest request) throws NoContentException {
        var account = _accountRepository.findById(request.getId())
                .orElseThrow(() -> new NoContentException("Account with id: " + request.getId() + " not found"));

        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        
        _accountRepository.save(account);
        return new AccountResponse(account);
    }

    /**
     * Update account address
     * @param request AddressRequest
     * @throws NoContentException exception
     */
    public void addAddress(AddressRequest request) throws NoContentException {
        var account = getUserById(request.getId());

        var address = new UserAddress(account, request);
        userAddressRepository.save(address);
    }

    /**
     * Get user by ID
     * @param id Long
     * @return Account
     * @throws NoContentException exception
     */
    public Account getUserById(Long id) throws NoContentException {
        return _accountRepository.findById(id)
                .orElseThrow(() -> new NoContentException("Account with id: " + id + " not found"));
    }
}
