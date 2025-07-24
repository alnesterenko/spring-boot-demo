package com.example.demo.rest.transferdatainhttpbody.controllers;

import com.example.demo.rest.transferdatainhttpbody.model.PaymentDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class PaymentController {

    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    @PostMapping("/payment")
    public ResponseEntity<PaymentDetails> makePayment(
            /* Извлекаем информацию о платеже из тела HTTP-запроса
            * !!! Она должна быть в формате JSON !!! */
            @RequestBody PaymentDetails paymentDetails) {
        /* Выводим в консоль сервера сумму платежа */
        logger.info("Received payment " + paymentDetails.getAmount());
        /* Возвращаем объект с информацией о платеже в теле HTTP-ответа и присваиваем ответу статус 202 Accepted */
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(paymentDetails);
    }
}
