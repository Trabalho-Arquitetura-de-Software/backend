package ucsal.br.api.management_service.utils.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
