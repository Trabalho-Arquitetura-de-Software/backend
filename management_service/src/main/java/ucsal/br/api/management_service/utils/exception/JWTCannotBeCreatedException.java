package ucsal.br.api.management_service.utils.exception;

public class JWTCannotBeCreatedException extends RuntimeException {
    public JWTCannotBeCreatedException(String message) {
        super(message);
    }

    public JWTCannotBeCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
