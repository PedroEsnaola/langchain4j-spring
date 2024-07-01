package com.example.langchain4jspring.domain.ai.repository;

import com.example.langchain4jspring.domain.ai.model.MongoChatMemory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMemoryRepository extends MongoRepository<MongoChatMemory, String> {
}
