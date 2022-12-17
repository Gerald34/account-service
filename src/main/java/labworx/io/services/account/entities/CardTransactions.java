package labworx.io.services.account.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class CardTransactions extends AbstractDateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private BankCard bankCard;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Getter
    public enum TransactionType {
        DEPOSIT("funds deposited in to account"),
        WITHDRAWAL("Funds withdrawn"),
        EFT("Paid via eft"),
        TRANSFER("Transferred to internal account"),
        DIRECT_PURCHASE("Purchased using bank account"),
        ONLINE_PURCHASE("Online purchase");

        private final String label;
        TransactionType(String label){
            this.label= label;
        }
    }

    public CardTransactions(BankCard bankCard, BigDecimal amount, TransactionType type) {
        this.bankCard = bankCard;
        this.amount = amount;
        this.type = type;
    }
}
