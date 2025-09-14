package cz.engeto.genesis_resources.exception;

public class PersonIdAlreadyExistsException extends RuntimeException {
    public PersonIdAlreadyExistsException(String personID) {
        super("Osobní ID: " + personID + " již existuje.");
    }
}
