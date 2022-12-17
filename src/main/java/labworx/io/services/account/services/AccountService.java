package labworx.io.services.account.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.transaction.Transactional;
import labworx.io.services.account.dto.requests.AccountRegistrationRequest;
import labworx.io.services.account.dto.requests.AccountRequest;
import labworx.io.services.account.dto.requests.AddressRequest;
import labworx.io.services.account.dto.responses.AccountResponse;
import labworx.io.services.account.entities.Account;
import labworx.io.services.account.entities.UserAddress;
import labworx.io.services.account.exceptions.BankAccountException;
import labworx.io.services.account.exceptions.NoContentException;
import labworx.io.services.account.exceptions.NoRecordFoundException;
import labworx.io.services.account.exceptions.ResourceException;
import labworx.io.services.account.repositories.AccountRepository;
import labworx.io.services.account.repositories.UserAddressRepository;
import labworx.io.services.account.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository _accountRepository;
    private final UserAddressRepository _userAddressRepository;
    private final PasswordEncoder _passwordEncoder;
    protected final FacilityService facilityService;
private final IDValidationService idValidationService;
    /**
     * Fetch accounts
     * @param pageable Pageable
     * @return Pageable AccountResponse
     */
    public Page<AccountResponse> getAccounts(Pageable pageable) {
        return _accountRepository.findAll(pageable).map(AccountResponse::new);
    }

    public Account getByUsername(String username) throws NoRecordFoundException {
        return _accountRepository.findByUsername(username).orElseThrow(() -> new NoRecordFoundException("Account not found"));
    }

    /**
     * Create new account
     * @param request AccountRegistrationRequest
     * @return AccountResponse
     */
    @Transactional
    public AccountResponse createAccount(AccountRegistrationRequest request) throws JsonProcessingException {

        // Validate user ID number
        var dto = new IDValidationService.ValidationDto();
        dto.setIdno(request.getIdentityNumber());
        if (!idValidationService.validate(dto).getValid())
            throw new BankAccountException("ID number is not a valid SA ID number");

        // Check if email exists
        if(verifyEmail(request.getEmail()))
            throw new ResourceException("Email already taken");

        // Check if email is valid
        if (!ValidationUtil.isEmailValid(request.getEmail()))
            throw new ResourceException("Email not valid");

        var account = new Account(request);
        account.setPassword(_passwordEncoder.encode(request.getPassword()));
        log.info("saving account");
        _accountRepository.save(account);

        facilityService.createFacility(account);

        // Todo
        // Send email verification email

        // Todo
        // Create confirmation token
        // Entity | Service | configuration
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
        var account = getAccountById(request.getId());

        var address = new UserAddress(account, request);
        _userAddressRepository.save(address);
    }

    /**
     * Get user by ID
     * @param id Long
     * @return Account
     * @throws NoContentException exception
     */
    public Account getAccountById(Long id) throws NoContentException {
        return _accountRepository.findById(id)
                .orElseThrow(() -> new NoContentException("Account with id: " + id + " not found"));
    }
}
