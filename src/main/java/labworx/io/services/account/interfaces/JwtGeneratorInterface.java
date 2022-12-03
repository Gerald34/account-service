package labworx.io.services.account.interfaces;


import labworx.io.services.account.entities.Account;

import java.util.Map;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken(Account account);
}
