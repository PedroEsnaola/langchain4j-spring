package com.example.langchain4jspring.domain.ai.config.model.vertexai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatModelProperties {

    private String project;
    private String location;
    private String modelName;
    private Float temperature;
    private Integer maxOutputTokens;
    private Integer topK;
    private Float topP;
    private Integer maxRetries;
    private boolean enabled;
}
