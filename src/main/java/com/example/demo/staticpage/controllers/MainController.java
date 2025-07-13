package com.example.demo.staticpage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* Чтобы отметить класс как контроллер Spring MVC, применяется стереотипная аннотация @Controller,
 а чтобы связать действие контроллера с определенным HTTP-запросом — аннотация @RequestMapping. */

@Controller
public class MainController {

    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }
}
