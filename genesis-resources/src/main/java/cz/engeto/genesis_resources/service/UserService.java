package cz.engeto.genesis_resources.service;

import cz.engeto.genesis_resources.entity.User;
import cz.engeto.genesis_resources.exception.InvalidPersonIdException;
import cz.engeto.genesis_resources.exception.PersonIdAlreadyExistsException;
import cz.engeto.genesis_resources.exception.UserNotFoundException;
import cz.engeto.genesis_resources.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(String name, String surname, String personID) {
        if (userRepository.findByPersonID(personID).isPresent()) {
            throw new PersonIdAlreadyExistsException(personID);
        }
//        //Kontrola proti seznamu
//        if (!allowedPersonIds.contains(personID)) {
//            throw new InvalidPersonIdException(personID);
//        }

        User user = User.builder()
                .name(name)
                .surname(surname)
                .personID(personID)
                .uuid(UUID.randomUUID().toString())
                .build();

        return userRepository.save(user);
    }

    public List<User> getAllUsers()  {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, String name, String surname) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setName(name);
        user.setSurname(surname);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
