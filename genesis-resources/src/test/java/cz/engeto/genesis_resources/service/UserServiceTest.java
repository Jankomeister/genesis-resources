package cz.engeto.genesis_resources.service;

import cz.engeto.genesis_resources.entity.User;
import cz.engeto.genesis_resources.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void createUserShouldGenerateUuid() {
        Mockito.when(userRepository.findByPersonID(any())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        User user = userService.createUser("Jack", "Smith", "jXa4g3H7oPq2");
        assertNotNull(user.getUuid());
        assertEquals("Jack", user.getName());
    }
}
