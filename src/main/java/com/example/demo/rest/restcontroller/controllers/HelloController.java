package com.example.demo.rest.restcontroller.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!_2";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "Ciao!_2";
    }
}
