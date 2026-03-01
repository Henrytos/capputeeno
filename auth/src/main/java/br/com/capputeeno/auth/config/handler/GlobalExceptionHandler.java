package br.com.capputeeno.auth.config.handler;

import br.com.capputeeno.auth.application.services.exceptions.UserAlreadyExistsException;
import br.com.capputeeno.auth.application.services.exceptions.UserNotFoundException;
import br.com.capputeeno.auth.application.services.exceptions.WrongCredentialsException;
import br.com.capputeeno.auth.domain.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseMessageException> handlerUserAlreadyExistsException(UserAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessageException(ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseMessageException> handlerUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageException(ex.getMessage()));
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ResponseMessageException> handlerWrongCredentialsException(WrongCredentialsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageException(ex.getMessage()));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ResponseMessageException> handlerInvalidInputException(InvalidInputException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageException(ex.getMessage()));
    }

    private record ResponseMessageException(
            String message
    ){}

}

