package labworx.io.services.account.entities;

import labworx.io.services.account.dto.requests.AccountRegistrationRequest;
import labworx.io.services.account.enums.Roles;
import labworx.io.services.account.utils.StringEncryptor;
import lombok.*;

import jakarta.persistence.*;

import static labworx.io.services.account.entities.Account.Status.EMAIL_CONFIRMATION;


@RequiredArgsConstructor
@Entity
@Getter
@Setter
public class Account extends AbstractDateModel {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String identityNumber;
    private String phone;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private Status status;

    @Getter
    public enum Status {

        ACTIVE("Account status"),
        EMAIL_CONFIRMATION("User needs to confirm email address"),
        DEACTIVATED("Account deactivated"),
        DELETED("Account deleted");

        private final String label;
        Status(String label) {
            this.label = label;
        }
    }

    public Account(AccountRegistrationRequest request) {
        firstName = request.getFirstName();
        lastName = request.getLastName();
        email = request.getEmail();
        password = StringEncryptor.Encrypt(request.getPassword());
        identityNumber = request.getIdentityNumber();
        active = false;
        status = EMAIL_CONFIRMATION;
    }

}
