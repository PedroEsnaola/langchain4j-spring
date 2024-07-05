package com.example.langchain4jspring;

import com.example.langchain4jspring.domain.ai.service.UserAssistantService;
import com.example.langchain4jspring.domain.application.model.User;
import com.example.langchain4jspring.domain.application.service.UserService;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.Console;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@EnableConfigurationProperties
public class Langchain4jSpringApplication {

    @Autowired
    UserAssistantService userAssistantService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Langchain4jSpringApplication.class, args);
    }


    @PostConstruct
    public void init(){
        userService.createUser(new User(null, "Pedro", "um email", null));
        Scanner scanner = new Scanner(System.in);
        Set<String> set = Set.of("exit", "quit");
        System.out.println("\n\nPlease enter your question: ");
        String question = scanner.nextLine();
        String id = ObjectId.get().toString();
        while (!set.contains(question)) {
            String response = userAssistantService.chat(id,question);
            System.out.println(response);
            System.out.println("\n\nPlease enter your question: ");
            question = scanner.nextLine();
        }
    }
}
