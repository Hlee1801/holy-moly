package com.ps.userservice.service;

import com.ps.userservice.entity.User;
import com.ps.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable("allUser")
    public List<User> getAllUsers() {
        System.out.println("⏳ Querying DB...");
        return userRepository.findAll();
    }
}
