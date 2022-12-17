package labworx.io.services.account;

import labworx.io.services.account.configuration.properties.JwtProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"labworx.io.services.account.repositories"})
@EnableConfigurationProperties({ JwtProperties.class })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AccountApplication.class);
		application.setApplicationStartup(new BufferingApplicationStartup(10000));
		application.run(args);
	}

}
