package labworx.io.services.account.services;

import labworx.io.services.account.entities.Account;
import labworx.io.services.account.entities.Facility;
import labworx.io.services.account.exceptions.NoContentException;
import labworx.io.services.account.repositories.FacilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public void createFacility(Account account) {
        var facility = new Facility(account);
        facilityRepository.save(facility);
    }

    public Facility getFacilityByAccount(Account account) throws NoContentException {
        return facilityRepository.findByAccount(account).orElseThrow(() -> new NoContentException("Could not find facility linked to account"));
    }

    public Facility getByAccountId(Long accountId) throws NoContentException {
        return facilityRepository.findByAccountId(accountId).orElseThrow(NoContentException::new);
    }
}
