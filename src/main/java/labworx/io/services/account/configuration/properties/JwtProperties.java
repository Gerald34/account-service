package labworx.io.services.account.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security")
@Data
public class JwtProperties {
    private JWT jwt;
    private JWT jwtToken;

    @Data
    public static class JWT {
        private String secret;
        private String message;
    }
}
