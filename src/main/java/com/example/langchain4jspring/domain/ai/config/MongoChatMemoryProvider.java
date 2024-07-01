package com.example.langchain4jspring.domain.ai.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mongoChatMemoryProvider")
@RequiredArgsConstructor
public class MongoChatMemoryProvider implements ChatMemoryProvider {

    private final MongoDbChatMemoryStore memoryStore;

    @Override
    public ChatMemory get(Object memoryId) {
        return MessageWindowChatMemory.builder()
                .id(memoryId)
                .chatMemoryStore(memoryStore)
                .maxMessages(50)
                .build();
    }
}
