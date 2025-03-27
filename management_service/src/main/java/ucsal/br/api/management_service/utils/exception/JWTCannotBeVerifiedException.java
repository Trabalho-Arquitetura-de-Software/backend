package ucsal.br.api.management_service.utils.exception;

public class JWTCannotBeVerifiedException extends RuntimeException {
    public JWTCannotBeVerifiedException(String message) {
        super(message);
    }

    public JWTCannotBeVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
