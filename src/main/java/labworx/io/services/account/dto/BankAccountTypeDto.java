package labworx.io.services.account.dto;

import labworx.io.services.account.entities.Facility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountTypeDto {
    private Facility facility;
    private Long accountTypeId;

    public BankAccountTypeDto(Facility facility, Long id) {
        this.facility = facility;
        accountTypeId = id;
    }
}
