package cz.engeto.genesis_resources.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonIdValidatorTest {

    @Test
    void testValidPersonId() {
        PersonIdValidator validator = new PersonIdValidator();
        assertTrue(validator.isValid("jXa4g3H7oPq2"));
        assertFalse(validator.isValid("INVALIDID1234"));
    }
}