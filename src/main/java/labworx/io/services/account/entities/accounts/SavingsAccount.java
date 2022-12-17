package labworx.io.services.account.entities.accounts;

import javax.persistence.*;
import labworx.io.services.account.entities.AbstractDateModel;
import labworx.io.services.account.entities.BankCard;
import labworx.io.services.account.entities.Facility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SavingsAccount extends AbstractDateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Facility facility;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BankCard bankCard;

    public SavingsAccount(Facility facility, BankCard bankCard) {
        this.facility = facility;
        this.bankCard = bankCard;
    }
}
