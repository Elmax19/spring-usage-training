package by.elmax19.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus
public class NoEntityWithSuchId extends HttpClientErrorException {
    public NoEntityWithSuchId(String statusText) {
        super(HttpStatus.NOT_FOUND, statusText);
    }
}
