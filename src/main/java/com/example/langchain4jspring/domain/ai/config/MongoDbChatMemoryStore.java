package com.example.langchain4jspring.domain.ai.config;

import com.example.langchain4jspring.domain.ai.model.MongoChatMemory;
import com.example.langchain4jspring.domain.ai.service.ChatMemoryService;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MongoDbChatMemoryStore implements ChatMemoryStore {

    private final ChatMemoryService chatMemoryService;

    @Override
    public List<ChatMessage> getMessages(Object id) {
        return chatMemoryService.retrieveMemoryOrCreateIfNotExists((String) id).retrieveMessages();
    }

    @Override
    public void updateMessages(Object id, List<ChatMessage> list) {
        MongoChatMemory mongoChatMemory = chatMemoryService.retrieveMemoryOrCreateIfNotExists((String) id);
        mongoChatMemory.mapMessages(list);
        chatMemoryService.save(mongoChatMemory);
    }

    @Override
    public void deleteMessages(Object id) {
        chatMemoryService.deleteMessages((String) id);
    }
}
