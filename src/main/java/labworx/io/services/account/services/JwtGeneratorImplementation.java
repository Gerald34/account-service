package labworx.io.services.account.services;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import labworx.io.services.account.entities.Account;
import labworx.io.services.account.interfaces.JwtGeneratorInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class JwtGeneratorImplementation implements JwtGeneratorInterface {

    private final String secret = System.getenv("JWT_SECRET_KEY");
    private final String message = System.getenv("JWT_MESSAGE");

    @Override
    public Map<String, String> generateToken(Account account) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(account.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secret).compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }
}
