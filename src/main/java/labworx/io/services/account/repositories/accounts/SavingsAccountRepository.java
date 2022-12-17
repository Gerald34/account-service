package labworx.io.services.account.repositories.accounts;

import labworx.io.services.account.entities.Facility;
import labworx.io.services.account.entities.accounts.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    Optional<SavingsAccount> findByFacility(Facility facility);
}
