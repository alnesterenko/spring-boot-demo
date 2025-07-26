package com.example.demo.restpointsusing.resttemplate.controllers;

import com.example.demo.restpointsusing.resttemplate.models.Payment;
import com.example.demo.restpointsusing.resttemplate.proxy.PaymentsProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;


@RestController
public class PaymentsController {

    private final PaymentsProxy paymentsProxy;

    private static Logger logger = Logger.getLogger(PaymentsController.class.getName());

    public PaymentsController(PaymentsProxy paymentsProxy) {
        this.paymentsProxy = paymentsProxy;
    }
    /* Создаём действие контроллера и связываем его с путём /payment */
    @PostMapping("/payment")
    public Payment cratePayment(
            /* Извлекаем данные о платеже из тела запроса */
            @RequestBody Payment payment
    ) {
        String requestId = UUID.randomUUID().toString();

        logger.info("Create new requestId: " + requestId);
        /* Вызываем прокси-метод, который в свою очередь вызывает конечную точку сервиса платежа.
        * Получаем тело запроса и возвращаем его клиенту.  */
        return paymentsProxy.createPayment(payment);
    }
}
