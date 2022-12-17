package labworx.io.services.account.services;

import javax.transaction.Transactional;
import labworx.io.services.account.dto.BankAccountDto;
import labworx.io.services.account.dto.requests.BankAccountRequest;
import labworx.io.services.account.dto.responses.BankAccountResponse;
import labworx.io.services.account.entities.Account;
import labworx.io.services.account.entities.BankAccount;
import labworx.io.services.account.entities.Facility;
import labworx.io.services.account.exceptions.NoContentException;
import labworx.io.services.account.repositories.BankAccountRepository;
import labworx.io.services.account.services.accounts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static labworx.io.services.account.enums.BankAccountStatus.COMPLETED;
import static labworx.io.services.account.enums.BankAccountStatus.INITIALIZED;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final AccountService accountService;
    private final FacilityService facilityService;
    private final SavingsService savingsService;
    private final CurrentAccountService currentAccountService;
    private final BusinessAccountService businessAccountService;
    private final JointAccountService jointAccountService;
    private final InvestmentAccountService investmentAccountService;
    private final BankCardService bankCardService;

    /**
     * Create user bank account with default bank card
     * @param request BankAccountRequest
     * @return BankAccountResponse
     * @throws Exception BankAccountResponse
     */
    @Transactional
    public BankAccountResponse createBankAccount(BankAccountRequest request) throws Exception {
        var account = accountService.getAccountById(request.getAccountId());
        var facility = facilityService.getFacilityByAccount(account);
        request.setActive(false);

        // Create user bank account
        // Linked to user facility
        var bankAccount = new BankAccount(facility, request, getCardholderName(account));
        bankAccount.setStatus(INITIALIZED);
        bankAccountRepository.save(bankAccount);

        // Create account type card
        BankAccountDto bankAccountDto = switch (request.getType()) {
            case INVESTMENT -> investmentAccountService.create(request);
            case CURRENT -> currentAccountService.create(request);
            case BUSINESS -> businessAccountService.create(request);
            case JOINT -> jointAccountService.create(request);
            case SAVINGS ->  savingsService.create(bankAccount, request);
        };

        // Complete bank account process
        bankAccount.setStatus(COMPLETED);
        bankAccountRepository.save(bankAccount);
        return new BankAccountResponse(account, bankAccountDto);
    }

    public BankAccount getBankAccount(Facility facility) {
        return bankAccountRepository.findByFacility(facility);
    }

    /**
     * Activate bank account
     * @param accountId Long
     * @return BankAccountResponse
     * @throws NoContentException Exception
     */
    @Transactional
    public BankAccountResponse activate(Long accountId) throws NoContentException {

        var account = accountService.getAccountById(accountId);
        var facility = facilityService.getFacilityByAccount(account);
        var bankAccount = getBankAccount(facility);
        bankAccount.setActive(true);
        bankAccountRepository.save(bankAccount);
        return new BankAccountResponse(account, new BankAccountDto(bankAccount, bankCardService.getCards(facility)));
    }

    private String getCardholderName(Account account) {
        return account.getFirstName().charAt(0) +
                " " +
                account.getLastName();
    }

}
