package br.com.davydmaker.smartinvestment.exception;

public class SystemException extends RuntimeException {

    private final String message;

    public SystemException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
