package labworx.io.services.account.repositories;

import labworx.io.services.account.entities.BankAccount;
import labworx.io.services.account.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByFacility(Facility facility);
}
