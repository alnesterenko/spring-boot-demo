package com.example.demo.restpointsusing.webclient.controllers;

import com.example.demo.restpointsusing.webclient.models.Payment;
import com.example.demo.restpointsusing.webclient.proxy.PaymentsProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
    public Mono<Payment> cratePayment(
            /* Извлекаем данные о платеже из тела запроса */
            @RequestBody Payment payment
    ) {
        String requestId = UUID.randomUUID().toString();

        logger.info("Create new requestId: " + requestId);
        /* Вызываем прокси-метод, который в свою очередь вызывает конечную точку сервиса платежа. */
        return paymentsProxy.createPayment(requestId, payment);
    }
}
