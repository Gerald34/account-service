package labworx.io.services.account.dto.requests;

import lombok.Data;

@Data
public class AccountRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String streetName;
    private int streetNumber;
    private String suburb;
    private String city;
    private String country;
    private String phone;
}
