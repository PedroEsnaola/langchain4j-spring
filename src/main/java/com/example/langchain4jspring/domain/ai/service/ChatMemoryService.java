package com.example.langchain4jspring.domain.ai.service;


import com.example.langchain4jspring.domain.ai.model.MongoChatMemory;
import com.example.langchain4jspring.domain.ai.repository.ChatMemoryRepository;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMemoryService {

    private final ChatMemoryRepository repository;

    public Optional<MongoChatMemory> findById(String id) {
        return repository.findById(id);
    }

    public MongoChatMemory save(MongoChatMemory mongoChatMemory) {
        return repository.save(mongoChatMemory);
    }

    public MongoChatMemory retrieveMemoryOrCreateIfNotExists(String id) {
        return this.findById(id).orElseGet(() -> {
            MongoChatMemory mongoChatMemory = new MongoChatMemory();
            mongoChatMemory.setId(id);
            mongoChatMemory.setMessages(new ArrayList<>());
            return save(mongoChatMemory);
        });
    }

    public MongoChatMemory deleteMessages(String id) {
        MongoChatMemory mongoChatMemory = retrieveMemoryOrCreateIfNotExists(id);
        mongoChatMemory.setMessages(new ArrayList<>());
        return repository.save(mongoChatMemory);
    }


}
