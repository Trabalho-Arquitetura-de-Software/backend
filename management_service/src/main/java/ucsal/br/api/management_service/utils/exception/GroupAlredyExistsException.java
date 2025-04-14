package ucsal.br.api.management_service.utils.exception;

public class GroupAlredyExistsException extends RuntimeException {
    public GroupAlredyExistsException(String message) {
        super(message);
    }
    public GroupAlredyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
