package com.example.demo.rest.manageexception2.controllers;

import com.example.demo.rest.manageexception2.exceptions.NotEnoughMoneyException;
import com.example.demo.rest.manageexception2.model.ErrorDetails;
import com.example.demo.rest.manageexception2.model.PaymentDetails;
import com.example.demo.rest.manageexception2.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /* !!! Вполне логично платёж отправлять через POST !!! */
    @PostMapping("/payment")
    public ResponseEntity<PaymentDetails> makePayment(
            @RequestParam(required = false) Double pay
    ) {
        PaymentDetails paymentDetails = paymentService.processPayment(pay);
            /* В случае успешного завершения метода сервиса возвращаем HTTP-ответ со статусом Accepted
             и экземпляром PaymentDetails, содержащимся в теле ответа */
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(paymentDetails);
    }
}
