package com.example.langchain4jspring.domain.application.repository;

import com.example.langchain4jspring.domain.application.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByNameLike(String name);
}

