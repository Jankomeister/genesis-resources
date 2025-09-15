package cz.engeto.genesis_resources.controller;

import cz.engeto.genesis_resources.dto.UserDetailDto;
import cz.engeto.genesis_resources.entity.User;
import cz.engeto.genesis_resources.exception.UserNotFoundException;
import cz.engeto.genesis_resources.mapper.UserMapper;
import cz.engeto.genesis_resources.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.createUser(user.getName(), user.getSurname(), user.getPersonID());
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDetailDto(createdUser));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) Boolean detail) {
        List<User> users = userService.getAllUsers();

        if (Boolean.TRUE.equals(detail)) {
            return ResponseEntity.ok(users.stream().map(UserMapper::toDetailDto).toList());
        }
        return ResponseEntity.ok(users.stream().map(UserMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id, @RequestParam(required = false) Boolean detail) {
        User user = userService.getUserById(id);

        if (Boolean.TRUE.equals(detail)) {
            return ResponseEntity.ok(UserMapper.toDetailDto(user));
        }
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user.getId(), user.getName(), user.getSurname());

        return ResponseEntity.ok(UserMapper.toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}