package com.example.demo.transmission.inpath.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* Пример запроса:

http://localhost:8080/home?color=blue&name=Jane

*/

@Controller
public class MainController {

    @RequestMapping("/home")
    /* Определяем новый параметр для метода действия контроллера и сопровождаем этот параметр аннотацией @RequestParam */
    /* Также добавляем параметр типа Model, который используется для передачи данных из контроллера в представление */
    public String home(@RequestParam (required = false) String name,
                       /* (required = false) -- значит параметр не обязательный.
                       * Если не передать ошибка не выскочит, но переменная получит значение null,
                       * "со всеми вытекающими":
                       * вместо текста будет пустота, а цвет будет по умолчанию: чёрный. */
                       @RequestParam (required = false) String color,
                       Model page) {
        page.addAttribute("username", name);
        /* Контроллер передает в представление цвет, полученный от клиента */
        page.addAttribute("color", color);
        return "home.html";
    }
}
