package labworx.io.services.account.controllers;

import labworx.io.services.account.dto.requests.BankAccountRequest;
import labworx.io.services.account.dto.responses.BankAccountResponse;
import labworx.io.services.account.exceptions.NoContentException;
import labworx.io.services.account.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("bank-account")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @PostMapping("create")
    public ResponseEntity<BankAccountResponse> create(@RequestBody BankAccountRequest request) throws Exception {
        return ok(bankAccountService.createBankAccount(request));
    }

    @PutMapping("{accountId}/activate")
    public ResponseEntity<BankAccountResponse> activate(@PathVariable("accountId") Long accountId) throws NoContentException {
        return ok(bankAccountService.activate(accountId));
    }
}
