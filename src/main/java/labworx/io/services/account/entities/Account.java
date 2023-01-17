package labworx.io.services.account.entities;

import labworx.io.services.account.dto.requests.AccountRegistrationRequest;
import labworx.io.services.account.enums.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.*;

import static labworx.io.services.account.entities.Account.Status.EMAIL_CONFIRMATION;


@RequiredArgsConstructor
@Entity
@Getter
@Setter
public class Account extends AbstractDateModel implements UserDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String primaryContactNumber;
    private String identityNumber;
    private String phone;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Getter
    public enum Status {

        ACTIVE("Account status"),
        EMAIL_CONFIRMATION("User needs to confirm email address"),
        BLOCKED("Account blocked"),
        DEACTIVATED_BY_OWNER("Account deactivated"),
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
        username = request.getUsername();
        password = request.getPassword();
        primaryContactNumber = request.getPrimaryContactNumber();
        identityNumber = request.getIdentityNumber();
        active = false;
        status = EMAIL_CONFIRMATION;
        role = request.getRole();
    }

}
