package com.example.demo.rest.manageexception.controllers;

import com.example.demo.rest.manageexception.exceptions.NotEnoughMoneyException;
import com.example.demo.rest.manageexception.model.ErrorDetails;
import com.example.demo.rest.manageexception.model.PaymentDetails;
import com.example.demo.rest.manageexception.services.PaymentService;
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
    public ResponseEntity<?> makePayment(
            @RequestParam (required = false) String flag
    ) {
        /* Пытаемся вызвать метод сервиса processPayment() */
        try {
            PaymentDetails paymentDetails = paymentService.processPayment(flag);
            /* В случае успешного завершения метода сервиса возвращаем HTTP-ответ со статусом Accepted
             и экземпляром PaymentDetails, содержащимся в теле ответа */
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(paymentDetails);
        } catch (NotEnoughMoneyException e) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setMessage("Not enough money to make the payment.");
            /* Если выдано исключение типа NotEnoughMoneyException,
             передаем HTTP-ответ со статусом Bad Request и экземпляром ErrorDetails,
              содержащимся в теле ответа */
            return ResponseEntity
                    .badRequest()
                    .body(errorDetails);
        }
    }
}
