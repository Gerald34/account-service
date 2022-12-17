package labworx.io.services.account.services.accounts;

import labworx.io.services.account.dto.BankAccountDto;
import labworx.io.services.account.dto.requests.BankAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessAccountService {
    public BankAccountDto create(BankAccountRequest request) {
        return new BankAccountDto();

    }
}
