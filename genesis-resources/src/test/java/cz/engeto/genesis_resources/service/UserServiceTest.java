package cz.engeto.genesis_resources.service;

import cz.engeto.genesis_resources.entity.User;
import cz.engeto.genesis_resources.exception.InvalidPersonIdException;
import cz.engeto.genesis_resources.exception.PersonIdAlreadyExistsException;
import cz.engeto.genesis_resources.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository, new PersonIdValidator());
    }

    @Test
    void createUserShouldGenerateUuid() {
        Mockito.when(userRepository.findByPersonID(any())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        User user = userService.createUser("Jack", "Smith", "jXa4g3H7oPq2");

        assertNotNull(user.getUuid());
        assertEquals("Jack", user.getName());
    }

    @Test
    void createUserWithInvalidPersonIdShouldThrow() {
        Mockito.when(userRepository.findByPersonID(any())).thenReturn(Optional.empty());

        //Neplatné ID
        InvalidPersonIdException ex = assertThrows(
                InvalidPersonIdException.class,
                () -> userService.createUser("Franta", "Pepa", "INVALIDID1234")
        );

        assertEquals("Uvedené osobní ID: INVALIDID1234 není platné!", ex.getMessage());
    }

    @Test
    void createUserWithDuplicatePersonIdShouldThrow() {
        User existingUser = new User();
        existingUser.setPersonID("jXa4g3H7oPq2");

        Mockito.when(userRepository.findByPersonID("jXa4g3H7oPq2"))
                .thenReturn(Optional.of(existingUser));

        PersonIdAlreadyExistsException ex = assertThrows(
                PersonIdAlreadyExistsException.class,
                () -> userService.createUser("Mojmír", "Středověký", "jXa4g3H7oPq2")
        );

        assertEquals("Osobní ID: jXa4g3H7oPq2 již existuje.", ex.getMessage());
    }
}
