package com.example.demo.restpointsusing.openfeign.controllers;

import com.example.demo.restpointsusing.openfeign.models.Payment;
import com.example.demo.restpointsusing.openfeign.proxy.PaymentsProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

/* Обращаемся к payments через интерфейс-прокладку PaymentsProxy */

@RestController
public class PaymentsController {

    private final PaymentsProxy paymentsProxy;

    private static Logger logger = Logger.getLogger(PaymentsController.class.getName());

    public PaymentsController(PaymentsProxy paymentsProxy) {
        this.paymentsProxy = paymentsProxy;
    }

    @PostMapping("/payment")
    public Payment cratePayment(
            @RequestBody Payment payment
    ) {
        String requestId = UUID.randomUUID().toString();

        logger.info("Create new requestId: " + requestId);

        return paymentsProxy.createPayment(requestId, payment);
    }
}
