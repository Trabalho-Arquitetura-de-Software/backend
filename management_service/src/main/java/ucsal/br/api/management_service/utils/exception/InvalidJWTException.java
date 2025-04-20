package ucsal.br.api.management_service.utils.exception;

public class InvalidJWTException extends RuntimeException {
    public InvalidJWTException(String message) {
        super(message);
    }
    public InvalidJWTException(String message, Throwable cause) {
        super(message, cause);
    }
}
