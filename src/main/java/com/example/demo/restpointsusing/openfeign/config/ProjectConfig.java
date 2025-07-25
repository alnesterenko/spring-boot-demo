package com.example.demo.restpointsusing.openfeign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
/* Активируем клиентов OpenFeign и сообщаем зависимости OpenFeign, где следует искать прокси-контракты */
@EnableFeignClients(basePackages = "com.example.demo.restpointsusing.openfeign.proxy")
public class ProjectConfig {
}
