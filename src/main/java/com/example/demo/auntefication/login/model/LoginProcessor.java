package com.example.demo.auntefication.login.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/* С помощью стереотипной аннотации сообщаем Spring, что это бин */
@Component
/* С помощью аннотации @RequestScope ограничиваем область видимости бина текущим запросом.
 Теперь Spring будет создавать новый экземпляр класса для каждого HTTP-запроса */
@RequestScope
public class LoginProcessor {
/* Учетные данные хранятся в бине в качестве атрибутов */
    private String username;
    private String password;
/* В бине определен метод, в котором реализована логика аутентификации */
    public boolean login() {
        /* Спорное решение, "но, окей"(с). */
        String username = this.getUsername();
        String password = this.getPassword();

        if ("natalie".equals(username) && "password".equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
