package labworx.io.services.account.services.accounts;

import javax.transaction.Transactional;
import labworx.io.services.account.dto.BankAccountDto;
import labworx.io.services.account.dto.requests.BankAccountRequest;
import labworx.io.services.account.entities.BankAccount;
import labworx.io.services.account.entities.BankCard;
import labworx.io.services.account.entities.accounts.SavingsAccount;
import labworx.io.services.account.exceptions.BankAccountException;
import labworx.io.services.account.repositories.accounts.SavingsAccountRepository;
import labworx.io.services.account.services.BankCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavingsService {
    private final SavingsAccountRepository savingsAccountRepository;
    private final BankCardService cardService;

    @Transactional
    public BankAccountDto create(BankAccount bankAccount, BankAccountRequest request) throws Exception {
        Optional<SavingsAccount> account = savingsAccountRepository.findByFacility(bankAccount.getFacility());
        if (account.isPresent())
            throw new BankAccountException("Can only have one savings account per facility");

        // Create card
        BankCard bankCard = new BankCard(bankAccount.getFacility(), request.getType());
        bankCard.setName(request.getType().toString());
        var savings = new SavingsAccount(bankAccount.getFacility(), bankCard);
        savingsAccountRepository.save(savings);

        return new BankAccountDto(bankAccount, cardService.createBankCard(bankCard));
    }
}
