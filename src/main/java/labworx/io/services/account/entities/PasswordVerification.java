package labworx.io.services.account.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PasswordVerification {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Account account;
    private String verification;

    public PasswordVerification(Account account, String verification) {
        this.account = account;
        this.verification = verification;
    }
}
