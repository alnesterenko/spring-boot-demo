package com.example.demo.auntefication.redirect.controllers;

import com.example.demo.auntefication.redirect.model.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* Используем стереотипную аннотацию @Controller, чтобы показать, что данный класс является контроллером */
@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;
/* При создании LoginController, создаётся и внедряется бин LoginProcessor */
    public LoginController(LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
    }

    /* Связываем действие контроллера с корневым путем приложения (/) */
    @GetMapping("/")
    public String loginGet() {
        /* Возвращаем имя представления, которое должно быть сформировано приложением */
        return "login.html";
    }

    /* Связываем действие контроллера с HTTP-запросом типа POST, отправляемым со страницы аутентификации */
    @PostMapping("/")
    public String loginPost(
            /* Извлекаем учетные данные из параметров HTTP-запроса */
            @RequestParam String username,
            @RequestParam String password,
            Model page
    ) {
        /* В LoginProcessor "вбиваем" данные */
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        /* И затем их проверяем */
        boolean loggedIn = loginProcessor.login();
        /* После успешной аунтификации приложение перенаправляет пользователя на главную страницу */
        if (loggedIn) {
            return "redirect:/main";
        }
        page.addAttribute("message", "Login failed!");
        return "login.html";
    }
}
