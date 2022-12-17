package labworx.io.services.account.controllers;

import labworx.io.services.account.dto.CardDto;
import labworx.io.services.account.dto.requests.CardPinRequest;
import labworx.io.services.account.dto.responses.GenericResponse;
import labworx.io.services.account.entities.BankCard;
import labworx.io.services.account.services.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("card")
public class BankCardController {
    private final BankCardService cardService;

    @PostMapping("{accountId}add")
    public ResponseEntity<List<CardDto>> add(
            @PathVariable("accountId") Long accountId,
                    @RequestBody BankCard bankCard) throws Exception {
        return ok(cardService.addBankCard(accountId, bankCard));
    }

    @PostMapping("activate")
    public ResponseEntity<GenericResponse> activate(@RequestBody CardDto cardDto) {
        return ok(cardService.activate(cardDto));
    }

    @PostMapping("deactivate")
    public ResponseEntity<GenericResponse> deactivate(@RequestBody CardDto cardDto) {
        return ok(cardService.deactivate(cardDto));
    }

    @PostMapping("change-pin")
    public ResponseEntity<GenericResponse> changeCardPin(@RequestBody CardPinRequest pinRequest) {
        return ok(cardService.changePin(pinRequest));
    }
}
