package cz.engeto.genesis_resources.exception;

public class InvalidPersonIdException extends RuntimeException {
    public InvalidPersonIdException(String personID) {
        super("Uvedené osobní ID: " + personID + " není platné!");
    }
}
