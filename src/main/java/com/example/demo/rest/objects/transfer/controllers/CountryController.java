package com.example.demo.rest.objects.transfer.controllers;

import com.example.demo.rest.objects.transfer.model.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* Обозначаем класс как REST-контроллер, чтобы Spring добавил бин в контекст и чтобы диспетчер сервлетов не искал
 представление после окончания работы этого метода */
@RestController
public class CountryController {

    /* Связываем действие контроллера с HTTP-методом GET и путем /france */
    @GetMapping("/france")
    public Country france() {
        Country country = Country.of("France", 67);
        /* franceВозвращаем экземпляр типа Country */
        return country;
    }

    @GetMapping("/all")
    public List<Country> countries() {
        Country c1 = Country.of("France", 67);
        Country c2 = Country.of("Spain", 47);
        /* Возвращает коллекцию в теле HTTP-ответа */
        return List.of(c1, c2);
    }
}
