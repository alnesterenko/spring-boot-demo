package com.example.demo.auntefication.count.auntefication.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/* Добавляем стереотипную аннотацию @Service, чтобы Spring создал бин этого класса и добавил его в контекст */
@Service
/* С помощью аннотации @SessionScope меняем область видимости бина на видимость в рамках сессии */
@SessionScope
public class LoggedUserManagementService {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
