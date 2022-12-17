package labworx.io.services.account.repositories;

import labworx.io.services.account.entities.Account;
import labworx.io.services.account.entities.PasswordVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordVerificationRepository extends JpaRepository<PasswordVerification, Long> {
    PasswordVerification findByAccount(Account account);
    void deleteByVerification(String verification);
}
