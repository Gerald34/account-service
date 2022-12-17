package labworx.io.services.account.entities;

import javax.persistence.*;
import labworx.io.services.account.dto.requests.BankAccountRequest;
import labworx.io.services.account.enums.BankAccountStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class BankAccount extends AbstractDateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Facility facility;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Boolean active;
    private String accountHolder;
    private BankAccountStatus status;

    @Getter
    public enum Type {
        SAVINGS("Savings account", "12"),
        CURRENT("Cheque account", "22"),
        BUSINESS("Business account", "43"),
        JOINT("Joint account with 5 accounts max", "66"),
        INVESTMENT("Investment savings account", "76");

        private final String label;
        public final String prefix;
        Type(String label, String prefix) {
            this.label = label;
            this.prefix = prefix;
        }
    }

    public BankAccount(Facility facility, BankAccountRequest accountRequest, String holder) {
        this.facility = facility;
        this.type = accountRequest.getType();
        this.active = accountRequest.getActive();
        this.accountHolder = holder;
    }
}
