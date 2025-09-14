package cz.engeto.genesis_resources.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class PersonIdValidator {

    private final Set<String> validPersonIds = new HashSet<>();

    public PersonIdValidator() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/dataPersonId.txt")))) {
            String line;

            while ((line = reader.readLine()) != null) {
                validPersonIds.add(line.trim());
            }
        } catch (Exception e) {
            throw new RuntimeException("Nepodařilo se načíst dataPersonId.txt", e);
        }
    }

    public boolean isValid(String personID) {
        return validPersonIds.contains(personID);
    }
}
