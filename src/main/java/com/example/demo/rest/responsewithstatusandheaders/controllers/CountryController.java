package com.example.demo.rest.responsewithstatusandheaders.controllers;

import com.example.demo.rest.responsewithstatusandheaders.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* Обозначаем класс как REST-контроллер, чтобы Spring добавил бин в контекст и чтобы диспетчер сервлетов не искал
 представление после окончания работы этого метода */
@RestController
public class CountryController {

    /* Связываем действие контроллера с HTTP-методом GET и путем /france */
    @GetMapping("/france")
    public ResponseEntity<Country> france() {
        Country country = Country.of("France", 67);
        /* franceВозвращаем экземпляр типа Country */
        return ResponseEntity
                /* Меняем статус HTTP-ответа на 202 Accepted */
                .status(HttpStatus.ACCEPTED)
                /* Добавляем к HTTP-ответу три дополнительных заголовка */
                .header("continent", "Europe")
                .header("capital", "Paris")
                .header("favorite_food", "cheese and wine")
                /* Создаем тело HTTP-ответа */
                .body(country);
    }

    @GetMapping("/all")
    public List<Country> countries() {
        Country c1 = Country.of("France", 67);
        Country c2 = Country.of("Spain", 47);
        /* Возвращает коллекцию в теле HTTP-ответа */
        return List.of(c1, c2);
    }
}
