package labworx.io.services.account.dto.responses;

import labworx.io.services.account.entities.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AuthorizationResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Authorisation authorisation;

    public AuthorizationResponse(Account account, String token, String type) {
        firstName = account.getFirstName();
        lastName = account.getLastName();
        email = account.getEmail();
        authorisation = new Authorisation(token, type);
    }

    @Getter
    @Setter
    public static class Authorisation {
        private String token;
        public String type;
        public LocalTime expiresIn;

        public Authorisation(String token, String type) {
            this.token = token;
            this.type = type;
        }
    }
}
