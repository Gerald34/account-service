package labworx.io.services.account.enums;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;

@Getter
public enum BankAccountStatus {
    INITIALIZED("Bank account initialized"),
    CARD_CREATED("Card linked to bank account created"),
    COMPLETED("Successful bank account");

    @Enumerated(EnumType.STRING)
    private final String label;
    BankAccountStatus (String label) {
        this.label= label;
    }
}
