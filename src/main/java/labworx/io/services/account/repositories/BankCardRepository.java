package labworx.io.services.account.repositories;

import labworx.io.services.account.entities.BankCard;
import labworx.io.services.account.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    List<BankCard> findByFacility(Facility facility);
}
