package br.com.capputeeno.auth.application.services.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Dados invalidos");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }


}
