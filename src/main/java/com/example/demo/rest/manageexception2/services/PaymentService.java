package com.example.demo.rest.manageexception2.services;

import com.example.demo.rest.manageexception2.exceptions.NotEnoughMoneyException;
import com.example.demo.rest.manageexception2.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDetails processPayment(Double pay) {
        if (pay != null) {
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setAmount(pay);
            return paymentDetails;
        }
        throw new NotEnoughMoneyException();
    }
}
