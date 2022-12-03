package labworx.io.services.account.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private Long id;
    private String streetName;
    private int streetNumber;
    private String suburb;
    private String city;
    private String country;
}
