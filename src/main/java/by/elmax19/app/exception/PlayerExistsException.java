package by.elmax19.app.exception;

public class PlayerExistsException extends RuntimeException {
    public PlayerExistsException(String message) {
        super(message);
    }
}
