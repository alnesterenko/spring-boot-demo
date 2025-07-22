package com.example.demo.auntefication.count.auntefication.model;

import com.example.demo.auntefication.count.auntefication.services.LoggedUserManagementService;
import com.example.demo.auntefication.count.auntefication.services.LoginCountService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/* С помощью стереотипной аннотации сообщаем Spring, что это бин */
@Component
/* С помощью аннотации @RequestScope ограничиваем область видимости бина текущим запросом.
 Теперь Spring будет создавать новый экземпляр класса для каждого HTTP-запроса */
@RequestScope
public class LoginProcessor {

    private final LoggedUserManagementService loggedUserManagementService;

    private final LoginCountService loginCountService;

/* Учетные данные хранятся в бине в качестве атрибутов */
    private String username;
    private String password;

    /* Автомонтируем бин LoggedUserManagementService */
    public LoginProcessor(LoggedUserManagementService loggedUserManagementService,
                          /* Внедряем бин LoginCountService через параметры конструктора */
                          LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    /* В бине определен метод, в котором реализована логика аутентификации */
    public boolean login() {
        /* Увеличиваем счётчик при каждой ПОПЫТКЕ аунтификации */
        loginCountService.increment();

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
