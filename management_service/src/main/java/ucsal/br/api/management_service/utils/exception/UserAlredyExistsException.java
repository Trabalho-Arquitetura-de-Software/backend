package ucsal.br.api.management_service.utils.exception;

public class UserAlredyExistsException extends RuntimeException {
    public UserAlredyExistsException(String message) {
        super(message);
    }

    public UserAlredyExistsException(String massage, Throwable cause) {
        super(massage, cause);
    }
}
