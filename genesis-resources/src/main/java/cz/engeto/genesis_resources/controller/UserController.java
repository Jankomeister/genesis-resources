package cz.engeto.genesis_resources.controller;

import cz.engeto.genesis_resources.entity.User;
import cz.engeto.genesis_resources.exception.UserNotFoundException;
import cz.engeto.genesis_resources.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getName(), user.getSurname(), user.getPersonID());
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) Boolean detail) {
        List<User> users = userService.getAllUsers();

        if (Boolean.TRUE.equals(detail)) return users; //Dodělám později
        // Mapování jen id, name, surname pak
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id, @RequestParam(required = false) Boolean detail) {
        return userService.getUserById(id)
                .orElseThrow() -> new // OPRAVIT
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user.getId(), user.getName(), user.getSurname());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
