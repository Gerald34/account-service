package labworx.io.services.account.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse {
    private Boolean isError;
    private String message;

    public GenericResponse(Boolean error, String message) {
        isError = error;
        this.message = message;
    }
}
