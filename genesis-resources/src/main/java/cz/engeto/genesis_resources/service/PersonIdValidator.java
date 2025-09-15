package cz.engeto.genesis_resources.service;

import cz.engeto.genesis_resources.exception.PersonIdFileNotFoundException;
import cz.engeto.genesis_resources.exception.PersonIdLoadException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class PersonIdValidator {

    private final Set<String> validPersonIds = new HashSet<>();

    public PersonIdValidator() {
        try (InputStream is = getClass().getResourceAsStream("/dataPersonId.txt")) {
            if (is == null) {
                throw new PersonIdFileNotFoundException("dataPersonId.txt");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    validPersonIds.add(line.trim());
                }
            }
        } catch (Exception e) {
            throw new PersonIdLoadException("dataPersonId.txt", e);
        }
    }

    public boolean isValid(String personID) {
        return validPersonIds.contains(personID);
    }
}
