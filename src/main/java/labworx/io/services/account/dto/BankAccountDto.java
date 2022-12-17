package labworx.io.services.account.dto;

import labworx.io.services.account.entities.BankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BankAccountDto {
    private Long id;
    private BankAccount.Type type;
    private Boolean active;
    private String accountHolder;
    private List<CardDto> cards;
    private Date createdDate;
    private Date lastModifiedDate;

    public BankAccountDto(BankAccount bankAccount, List<CardDto> cardDto) {
        id = bankAccount.getId();
        accountHolder = bankAccount.getAccountHolder();
        type = bankAccount.getType();
        active = bankAccount.getActive();
        cards = cardDto;
        createdDate = bankAccount.getCreatedDate();
        lastModifiedDate = bankAccount.getLastModifiedDate();
    }
}
