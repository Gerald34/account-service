package labworx.io.services.account.repositories;

import labworx.io.services.account.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Page<Account> findAll(Pageable pageable);
    Account findByEmail(String email);
    Account findAllByEmail(String email);
    Optional<Account> findById(Long id);
}
