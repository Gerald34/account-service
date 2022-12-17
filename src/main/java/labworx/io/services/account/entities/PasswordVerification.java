package labworx.io.services.account.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PasswordVerification {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
    private String verification;

    public PasswordVerification(Account account, String verification) {
        this.account = account;
        this.verification = verification;
    }
}
