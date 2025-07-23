package com.example.demo.rest.manageexception.services;

import com.example.demo.rest.manageexception.exceptions.NotEnoughMoneyException;
import com.example.demo.rest.manageexception.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDetails processPayment(String flag) {
        if (flag != null && flag.equals("true")) {
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setAmount(100.0);
            return paymentDetails;
        }
        throw new NotEnoughMoneyException();
    }
}
