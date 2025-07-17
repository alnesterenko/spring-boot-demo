package com.example.demo.auntefication.redirect.model;

import com.example.demo.auntefication.redirect.services.LoggedUserManagementService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/* С помощью стереотипной аннотации сообщаем Spring, что это бин */
@Component
/* С помощью аннотации @RequestScope ограничиваем область видимости бина текущим запросом.
 Теперь Spring будет создавать новый экземпляр класса для каждого HTTP-запроса */
@RequestScope
public class LoginProcessor {

    private final LoggedUserManagementService loggedUserManagementService;

/* Учетные данные хранятся в бине в качестве атрибутов */
    private String username;
    private String password;

    /* Автомонтируем бин LoggedUserManagementService */
    public LoginProcessor(LoggedUserManagementService loggedUserManagementService) {
        this.loggedUserManagementService = loggedUserManagementService;
    }

    /* В бине определен метод, в котором реализована логика аутентификации */
    public boolean login() {
        /* Спорное решение, "но, окей"(с). */
        String username = this.getUsername();
        String password = this.getPassword();

        boolean loginResult = false;
        if ("natalie".equals(username) && "password".equals(password)) {
            /* Сохраняем имя пользователя в бине LoggedUserManagementService */
            loggedUserManagementService.setUsername(username);
            loginResult = true;
        }
        return loginResult;
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
