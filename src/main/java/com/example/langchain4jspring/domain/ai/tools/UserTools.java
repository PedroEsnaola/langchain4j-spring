package com.example.langchain4jspring.domain.ai.tools;

import com.example.langchain4jspring.domain.application.model.User;
import com.example.langchain4jspring.domain.application.repository.UserRepository;
import com.example.langchain4jspring.domain.application.service.UserService;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTools {

    private final UserService userService;

    @Tool("Use essa tool para recuperar um usuario pelo id, o retorno contera as informacoes do usuario ou null caso nada seja encontrado")
    public User findById(String id) {
        return userService.findById(id).orElse(null);
    }

    @Tool("Use essa tool para buscar uma lista de usuarios pelo nome, caso o retorno traga mais de um usuario, liste-os e deixe que seja escolhido qual sera usado")
    public List<User> findByName(String name) {
        return userService.findByName(name);
    }

    @Tool("Use essa tool para atualizar um usuario, a atualizacao atualiza todos os campos, entao é necessario enviar a informacao que sera atualizada juntamente com as informacoes ja existentes do usuario")
    public User update(User user) {
        return userService.updateUser(user);
    }

}
