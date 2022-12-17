package labworx.io.services.account.entities.accounts;

import javax.persistence.*;
import labworx.io.services.account.entities.BankCard;
import labworx.io.services.account.entities.Facility;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BusinessAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Facility facility;
    private String companyRegistration;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BankCard bankCard;

    public BusinessAccount(Facility facility, BankCard bankCard) {
        this.facility = facility;
        this.bankCard = bankCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BusinessAccount that = (BusinessAccount) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
