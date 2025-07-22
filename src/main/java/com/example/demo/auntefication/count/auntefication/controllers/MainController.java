package com.example.demo.auntefication.count.auntefication.controllers;

import com.example.demo.auntefication.count.auntefication.services.LoggedUserManagementService;
import com.example.demo.auntefication.count.auntefication.services.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;

    private final LoginCountService loginCountService;

    /* Автомонтируем бин LoggedUserManagementService,
    * чтобы можно было узнать, аутонтифицирован ли пользователь. */
    public MainController(LoggedUserManagementService loggedUserManagementService,
                          /* Внедряем бин с областью видимости всего приложения. Без этого никак. */
                          LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
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
            /* После выхода по ссылке, сбрасываем счётчик попыток. */
            loginCountService.resetCount();
        }

        /* Получаем значение username -- если пользователь аунтифицирован, но оно не должно быть null */
        String username = loggedUserManagementService.getUsername();
        /* Получаем значение счётчика */
        int count = loginCountService.getCount();
        /* Если пользователь не аунтифицирован, то перенаправляем его на страницу аунтификации */
        if (username == null) {
            return "redirect:/";
        }
        /* Добавляем на главную страницу имя пользователя */
        page.addAttribute("username", username);
        /* и количество из счётчика */
        page.addAttribute("count", count);
        /* Если аунтифицирован, то возвращаем представление главной страницы */
        return "main.html";
    }
}
