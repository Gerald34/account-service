package labworx.io.services.account.exceptions;

public class BankAccountException extends RuntimeException {

    public BankAccountException() {
        super();
    }

    public BankAccountException(String message) {
        super(message);
    }

    public BankAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
