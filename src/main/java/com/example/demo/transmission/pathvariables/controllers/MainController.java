package com.example.demo.transmission.pathvariables.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* Данный способ передачи переменных можно совмещать с передачей в параметрах запроса.
* Проверено: работает !!! */

@Controller
public class MainController {

    /* Чтобы определить переменную пути, нужно присвоить ей имя и вставить это имя в путь,
     заключив в фигурные скобки */
    @RequestMapping("/home/{color}")
    /* Параметр, который должен принимать переменную пути, нужно отметить аннотацией @PathVariable.
     Имя параметра должно совпадать с именем переменной пути */
    public String home(@PathVariable String color,
                       Model page) {
        page.addAttribute("username", "Katy");
        page.addAttribute("color", color);
        return "home.html";
    }
}
