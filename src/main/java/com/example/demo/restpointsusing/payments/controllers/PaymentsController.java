package com.example.demo.restpointsusing.payments.controllers;

import com.example.demo.restpointsusing.payments.models.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class PaymentsController {
    /* Используем вывод в консоль и убеждаемся,
     что при вызове конечной точки соответствующий метод контроллера получает правильные данные */
    private static Logger logger = Logger.getLogger(PaymentsController.class.getName());

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(
            /* Конечная точка получает заголовок и тело запроса от вызывающего ее приложения.
             Эти данные передаются в метод контроллера в качестве параметров */
            @RequestHeader String requestId,
            @RequestBody Payment payment
    ) {
        logger.info("Received request with ID " + requestId
                + " ;Payment Amount: " + payment.getAmount());
        /* Метод присваивает платежу случайный ID */
        payment.setId(UUID.randomUUID().toString());
        /* IDДействие контроллера возвращает HTTP-ответ.
         У ответа есть заголовок и тело, в котором содержится платеж с присвоенным ему случайным ID */
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("requestId", requestId)
                .body(payment);
    }
}
