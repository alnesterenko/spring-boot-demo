package com.example.demo.auntefication.redirect.controllers;

import com.example.demo.auntefication.redirect.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;

    /* Автомонтируем бин LoggedUserManagementService,
    * чтобы можно было узнать, аутонтифицирован ли пользователь. */
    public MainController(LoggedUserManagementService loggedUserManagementService) {
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/main")
    public String home(
            /* Извлекаем из запроса параметр Logout, если он там есть */
            @RequestParam (required = false) String logout,
            Model page
    ) {
        /* Если в запросе есть параметр Logout, удаляем из бина LoggedUserManagementService имя пользователя */
        if (logout != null) {
            loggedUserManagementService.setUsername(null);
        }

        /* Получаем значение username -- если пользователь аунтифицирован, но оно не должно быть null */
        String username = loggedUserManagementService.getUsername();
        /* Если пользователь не аунтифицирован, то перенаправляем его на страницу аунтификации */
        if (username == null) {
            return "redirect:/";
        }
        /* Добавляем на главную страницу имя пользователя */
        page.addAttribute("username", username);
        /* Если аунтифицирован, то возвращаем представление главной страницы */
        return "main.html";
    }
}
