package labworx.io.services.account.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceException extends RuntimeException {
    private Boolean isError;
    private String message;

    public ResourceException(Boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }
}
