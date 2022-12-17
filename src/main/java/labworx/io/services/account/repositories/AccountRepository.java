package labworx.io.services.account.repositories;

import labworx.io.services.account.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Page<Account> findAll(Pageable pageable);
    Optional<Account> findByEmail(String email);
    Account findAllByEmail(String email);
    Optional<Account> findById(Long id);
}
