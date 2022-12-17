package labworx.io.services.account.dto;

import labworx.io.services.account.entities.BankCard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    public Long id;
    public String name;
    public Boolean primaryCard;
    public String accountNumber;
    public String cardNumber;
    public String pin;

    public CardDto(BankCard card) {
        id = card.getId();
        name = card.getName();
        primaryCard = card.getPrimaryCard();
        cardNumber = "**** **** **** *" + card.getCardNumber().charAt(13) + card.getCardNumber().charAt(14) + card.getCardNumber().charAt(15);
        accountNumber = card.getAccountNumber();
        pin = "*****";
    }
}
