package com.example.demo.rest.controller.responsebody.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    /* С помощью аннотации @ResponseBody сообщаем диспетчеру сервлетов,
     что этот метод возвращает не имя представления, а непосредственно HTTP-ответ */
    @ResponseBody
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/ciao")
    @ResponseBody
    public String ciao() {
        return "Ciao!";
    }
}
