package cz.engeto.genesis_resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(PersonIdAlreadyExistsException.class)
    public ResponseEntity<String> handlePersonIdAlreadyExists(PersonIdAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(InvalidPersonIdException.class)
    public ResponseEntity<String> handleInvalidPersonId(InvalidPersonIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(PersonIdFileNotFoundException.class)
    public ResponseEntity<String> handlePersonIdFileNotFound(PersonIdFileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(PersonIdLoadException.class)
    public ResponseEntity<String> handlePersonIdLoad(PersonIdLoadException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
    }
}