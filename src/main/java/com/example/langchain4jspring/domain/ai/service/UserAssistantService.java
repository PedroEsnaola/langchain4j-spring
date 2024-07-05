package com.example.langchain4jspring.domain.ai.service;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService(
        chatMemoryProvider = "mongoChatMemoryProvider",
        chatModel = "vertexAiGeminiChatModel",
        tools = "userTools"
)
public interface UserAssistantService {

    @SystemMessage("Voce é um assistente especializado em entender as preferencias de investimento de um usuario e devera guiar uma conversa para entende-las")
    String chat(@MemoryId String memoryId, @UserMessage String message);

}
