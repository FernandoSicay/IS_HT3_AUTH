package gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private String message;
    private HttpStatus status;

    public CustomException(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
