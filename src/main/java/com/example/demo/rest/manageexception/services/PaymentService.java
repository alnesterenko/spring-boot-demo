package com.example.demo.rest.manageexception.services;

import com.example.demo.rest.manageexception.exceptions.NotEnoughMoneyException;
import com.example.demo.rest.manageexception.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
