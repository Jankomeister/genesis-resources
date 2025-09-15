package cz.engeto.genesis_resources.exception;

public class PersonIdLoadException extends RuntimeException {
    public PersonIdLoadException(String fileName, Throwable cause) {
        super("Nepodařilo se načíst " + fileName, cause);
    }
}