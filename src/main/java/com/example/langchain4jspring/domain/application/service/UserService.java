package com.example.langchain4jspring.domain.application.service;

import com.example.langchain4jspring.domain.application.model.User;
import com.example.langchain4jspring.domain.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        log.info("Creating new user: {}", user);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Assert.notNull(user.getId(), "User must have an id");
        log.info("Updating user: {}", user);
        return userRepository.save(user);
    }

    public Optional<User> findById(String id){
        log.info("Finding user by id: {}", id);
        return userRepository.findById(id);
    }

    public List<User> findByName(String name){
        return userRepository.findByNameLike(name);
    }
}
