package cz.engeto.genesis_resources.exception;

public class PersonIdFileNotFoundException extends RuntimeException {
    public PersonIdFileNotFoundException(String fileName) {
        super("Soubor " + fileName + " nebyl nalezen!");
    }
}