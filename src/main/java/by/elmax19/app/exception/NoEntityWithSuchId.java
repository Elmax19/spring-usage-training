package by.elmax19.app.exception;

public class NoEntityWithSuchId extends RuntimeException {
    public NoEntityWithSuchId(String message) {
        super(message);
    }
}
