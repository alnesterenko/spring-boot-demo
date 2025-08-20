package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /* С помощью аннотации @Transactional сообщаем Spring,
    * что вызов этого метода должен происходить в рамках транзакции. */
    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        /* Получаем информацию о счетах, чтобы извлеч оттуда текущий баланс для каждого счёта.  */
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        /* Вычисляем новый баланс для счетов отправителя и получателя */
        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        /* Устанавливаем новые значения счёта для отправителя и получателя */
        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);
/* В конце метода выбрасываем исключение чтобы имитировать проблему в процессе транзакции. */
        throw new RuntimeException("О нет! Всё накрылось медным тазом!");
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
