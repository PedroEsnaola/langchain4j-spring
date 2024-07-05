package com.example.langchain4jspring.domain.ai.model;


import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.*;
import dev.langchain4j.memory.ChatMemory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "chat_memory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MongoChatMemory{

    @Id
    private String id;

    private List<Message> messages;

    @CreatedDate
    public LocalDateTime createdAt;

    @LastModifiedDate
    public LocalDateTime updatedAt;

    public void mapMessages(List<ChatMessage> messages) {
        this.messages = messages.stream().map(Message::from).toList();
    }

    public List<ChatMessage> retrieveMessages() {
        if(messages == null) return new ArrayList<>();
        return this.messages.stream().map(Message::toChatMessage).toList();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message{
        private ChatMessageType type;
        private String text;

        public static Message from(ChatMessage message) {
            switch (message.type()){
                case AI:
                    var aiMessage = (dev.langchain4j.data.message.AiMessage) message;
                    return new AiMessage(aiMessage.type(), aiMessage.text(), aiMessage.toolExecutionRequests());
                default:
                    return new Message(message.type(), message.text());
            }
        }

        public ChatMessage toChatMessage(){
            return switch (this.type){
                case SYSTEM -> new SystemMessage(this.text);
                default -> new UserMessage(this.text);
            };
        }
    }

    public static class AiMessage extends Message{
        private List<ToolExecutionRequest> toolExecutionRequests;

        @Override
        public ChatMessage toChatMessage() {
            return new dev.langchain4j.data.message.AiMessage(super.getText(), this.toolExecutionRequests);
        }

        public AiMessage(ChatMessageType type, String text, List<ToolExecutionRequest> toolExecutionRequests) {
            super(type, text);
            this.toolExecutionRequests = toolExecutionRequests;
        }
    }



}
