package labworx.io.services.account.entities;

import javax.persistence.*;
import labworx.io.services.account.dto.requests.CardRequest;
import labworx.io.services.account.utils.Generator;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Facility facility;
    private Boolean primaryCard;
    private String accountNumber;
    private String cardNumber;
    private String pin;
    private String cvv;
    private String expiryDate;

    public BankCard(Facility facility, BankAccount.Type type){
        this.facility = facility;
        accountNumber = type.getPrefix() + Generator.getRandomNumbers(8);
        cardNumber = Generator.getRandomNumbers(16);
        pin = Generator.getRandomNumbers(5);
        cvv = Generator.getRandomNumbers(3);
        expiryDate = setExpiryDate();
    }

    private String setExpiryDate() {
        return String.format( LocalDate.now().getMonth().getValue() + "/" + (LocalDate.now().getYear() + 5));
    }
}
