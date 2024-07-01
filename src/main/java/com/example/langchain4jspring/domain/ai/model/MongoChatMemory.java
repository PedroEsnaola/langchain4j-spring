package com.example.langchain4jspring.domain.ai.model;


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
            return new Message(message.type(), message.text());
        }

        public ChatMessage toChatMessage(){
            return switch (this.type){
                case AI -> new AiMessage(this.text);
                case SYSTEM -> new SystemMessage(this.text);
                default -> new UserMessage(this.text);
            };
        }
    }



}
