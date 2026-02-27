package br.com.capputeeno.auth.domain.exceptions;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException() {
        super("Dados invalidos");
    }

    public InvalidInputException(String message) {
        super(message);
    }

}
