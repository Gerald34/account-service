package labworx.io.services.account.repositories;

import labworx.io.services.account.entities.Account;
import labworx.io.services.account.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Optional<Facility> findByAccount(Account account);
    Optional<Facility> findByAccountId(Long accountId);
}
