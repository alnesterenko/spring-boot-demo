package com.example.demo.restpointsusing.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Для запуска нужно вынести пакет payments в отдельный проект,
 чтобы два приложения на Tomcat не конфликтовали из-за одного порта. */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}