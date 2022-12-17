package labworx.io.services.account.entities;

import labworx.io.services.account.dto.requests.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Account account;
    private String streetName;
    private int streetNumber;
    private String suburb;
    private String city;
    private String country;

    public UserAddress(Account account, AddressRequest addressRequest) {
        this.account = account;
        streetName = addressRequest.getStreetName();
        streetNumber = addressRequest.getStreetNumber();
        suburb = addressRequest.getSuburb();
        city = addressRequest.getCity();
        country = addressRequest.getCountry();
    }
}
