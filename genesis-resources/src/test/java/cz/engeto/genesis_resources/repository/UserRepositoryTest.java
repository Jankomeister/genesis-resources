package cz.engeto.genesis_resources.repository;

import cz.engeto.genesis_resources.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFind() {
        User user = User.builder()
                .name("Test")
                .surname("User")
                .personID("yB9fR6tK0wLm")
                .uuid(UUID.randomUUID().toString())
                .build();
        userRepository.save(user);
        Optional<User> found = userRepository.findByPersonID("yB9fR6tK0wLm");
        assertTrue(found.isPresent());
    }
}