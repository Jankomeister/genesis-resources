package cz.engeto.genesis_resources.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Uživatel s tímto ID: " + id + " nebyl nalezen.");
    }
}
