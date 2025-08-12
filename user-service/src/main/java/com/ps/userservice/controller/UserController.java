package com.ps.userservice.controller;

import com.ps.userservice.dto.UserDto;
import com.ps.userservice.entity.User;
import com.ps.userservice.repository.UserRepository;
import com.ps.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping
    @CacheEvict(value = "allUsers", allEntries = true)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    UserDto getUserById(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
