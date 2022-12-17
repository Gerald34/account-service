package labworx.io.services.account.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceException extends RuntimeException {

    public ResourceException(String message) {
        super(message);
    }
}
