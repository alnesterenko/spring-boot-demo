package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.service.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.TransferRequest;

import java.util.List;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    /* Используем для конечной точки /transfer HTTP-метод POST,
    *  так как вносим изменения в информацию в базе данных */
    @PostMapping("/transfer")
    public void transferMoney(
            /* Извлекаем из тела запроса необходимые данные
            * (id счёта отправителя и получателя, а также сумму перевода) */
            @RequestBody TransferRequest request) {
        /* Вызываем метод сервиса transferMoney() — транзакционный метод в котором,
         в котором реализован сценарий перевода денег */
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return transferService.getAllAccounts();
    }
}
