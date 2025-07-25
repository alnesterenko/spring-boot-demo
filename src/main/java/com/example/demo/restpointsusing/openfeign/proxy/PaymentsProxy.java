package com.example.demo.restpointsusing.openfeign.proxy;

import com.example.demo.restpointsusing.openfeign.models.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/* !!! Каждый метод, объявленный в интерфейсе, соответствует вызову конечной точки REST !!! */

/* Создаем REST-клиента с помощью аннотации @FeignClient.
 Минимальная конфигурация подразумевает имя и базовый URI конечной точки */
@FeignClient(name = "payments",
                /* Свойства проекта хранятся в файле application.properties
                и доступны в коде с помощью синтаксиса ${property_name}. */
                url = "${name.service.url}")
public interface PaymentsProxy {
/* Определяем путь и HTTP-метод конечной точки */
    @PostMapping("/payment")
    Payment createPayment(
            /* Определяем заголовки и тело HTTP-запроса */
            @RequestHeader String requestId,
            @RequestBody Payment payment);
}
