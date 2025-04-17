package ucsal.br.api.management_service.utils.exception;

public class ProjectAlredyExistsException extends RuntimeException {
    public ProjectAlredyExistsException(String message) {
        super(message);
    }
    public ProjectAlredyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
