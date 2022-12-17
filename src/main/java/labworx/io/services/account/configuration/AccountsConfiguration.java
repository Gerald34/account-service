package labworx.io.services.account.configuration;

import labworx.io.services.account.entities.Account;
import labworx.io.services.account.enums.Roles;
import labworx.io.services.account.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static labworx.io.services.account.enums.Roles.*;

@Configuration
@RequiredArgsConstructor
public class AccountsConfiguration {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void init() {
        createAccount();
    }

    public void createAccount() {
        initAccount("Codex", "Labworx", "code45dev@gmail.com", "Codex@1234",
                SUPER_USER);
        initAccount("Gerald", "Mathabela", "gerald@labworx.io", "Gerald@1234", USER);
        initAccount("Vusimuzi", "Khumalo", "khumalo@labworx.io", "Vusimuzi@1234", ADMIN);
        initAccount("Labworx", "Technologies", "accounts@labworx.io", "Labworx@1234", GROUP);
    }

    private void initAccount(String firstName, String lastName, String email, String password, Roles role) {
        Account account;
        if (accountRepository.findByEmail(email).isEmpty()) {
            account = new Account();
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            account.setUsername(email);
            account.setPassword(passwordEncoder.encode(password));
            account.setActive(true);
            account.setRole(role);

            accountRepository.save(account);
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.create();
    }

}
