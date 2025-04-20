package ucsal.br.api.management_service.utils.exception;

public class IsNotACoordinatorException extends RuntimeException {
    public IsNotACoordinatorException(String message) {
        super(message);
    }
    public IsNotACoordinatorException(String message, Throwable cause) {
      super(message, cause);
    }
}
