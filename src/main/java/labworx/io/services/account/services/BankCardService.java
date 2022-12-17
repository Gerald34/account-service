package labworx.io.services.account.services;

import javax.transaction.Transactional;
import labworx.io.services.account.dto.CardDto;
import labworx.io.services.account.dto.requests.CardPinRequest;
import labworx.io.services.account.dto.responses.GenericResponse;
import labworx.io.services.account.entities.BankCard;
import labworx.io.services.account.entities.Facility;
import labworx.io.services.account.exceptions.BankAccountException;
import labworx.io.services.account.repositories.BankCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankCardService {
    private final BankCardRepository bankCardRepository;
    private final FacilityService facilityService;

    public List<CardDto> addBankCard(Long accountId, BankCard bankCard) throws Exception {
        bankCard.setFacility(facilityService.getByAccountId(accountId));
        return createBankCard(bankCard);
    }

    @Transactional
    public List<CardDto> createBankCard(BankCard bankCard) {
        if (checkMaxCards(bankCard.getFacility()))
            throw new BankAccountException("Facility has reached a max amount of cards");

        bankCard.setPrimaryCard(isPrimary(bankCard.getFacility()));
        bankCardRepository.save(bankCard);

        return getCards(bankCard.getFacility());
    }

    // Get cards linked to facility
    public List<CardDto> getCards(Facility facility) {
       return bankCardRepository.findByFacility(facility).stream().map(CardDto::new).toList();
    }


    // Todo
    // Activate bank card
    public GenericResponse activate(CardDto cardDto) {
        return new GenericResponse(false, "Card has been activated.");
    }

    // Todo
    // Deactivate bank card
    public GenericResponse deactivate(CardDto cardDto) {
        return new GenericResponse(false, "Card name: " + cardDto.getName() + " has been deactivated.");
    }

    // Todo
    // Change card pin
    public GenericResponse changePin(CardPinRequest pinRequest) {
        return new GenericResponse(false, "Card pin has been changed");
    }

    private Boolean checkMaxCards(Facility facility) {
        int limit = 3;
        var cards =  bankCardRepository.findByFacility(facility);
        return cards.size() > limit;
    }

    private Boolean isPrimary(Facility facility) {
        var cards = bankCardRepository.findByFacility(facility);
        System.out.println(cards.size());
        return cards.size() == 1;
    }

}
