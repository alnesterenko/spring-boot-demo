package com.example.demo.restpointsusing.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfig {

    @Bean
    public WebClient webClient () {
        /* Создаем бин WebClient и добавляем его в контекст Spring */
        return WebClient
                .builder()
                .build();
    }
}
