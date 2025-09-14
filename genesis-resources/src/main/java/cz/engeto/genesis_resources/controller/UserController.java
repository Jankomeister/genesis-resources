package cz.engeto.genesis_resources.controller;

import cz.engeto.genesis_resources.dto.UserDetailDto;
import cz.engeto.genesis_resources.entity.User;
import cz.engeto.genesis_resources.exception.UserNotFoundException;
import cz.engeto.genesis_resources.mapper.UserMapper;
import cz.engeto.genesis_resources.service.UserService;
import jakarta.validation.Valid;
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
    public UserDetailDto createUser(@RequestBody @Valid User user) {
        User createdUser = userService.createUser(user.getName(), user.getSurname(), user.getPersonID());
        return UserMapper.toDetailDto(createdUser);
    }

    @GetMapping
    public List<?> getAllUsers(@RequestParam(required = false) Boolean detail) {
        List<User> users = userService.getAllUsers();

        if (Boolean.TRUE.equals(detail)) {
            return users.stream().map(UserMapper::toDetailDto).toList();
        }
        return users.stream().map(UserMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public Object getUser(@PathVariable Long id, @RequestParam(required = false) Boolean detail) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Uživatel nenalezen.")); // UDĚLAT SI SVOJÍ VÝJIMKU!

        return Boolean.TRUE.equals(detail) ? UserMapper.toDetailDto(user) : UserMapper.toDto(user);
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
