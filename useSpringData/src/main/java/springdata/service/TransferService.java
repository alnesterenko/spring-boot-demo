package springdata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springdata.model.Account;
import springdata.repository.AccountRepository;
import springdata.exception.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /* Помещаем логику исполнения сценария в транзакцию -- во избежание рассогласования данных в том случае,
    * если одна из операций завершится неудачно */
    @Transactional
    public void transferMoney(
            long idSender,
            long idReceiver,
            BigDecimal amount) {
        /* Получаем инф. о счетах отправителя и получателя */
        Account sender = accountRepository.findById(idSender).orElseThrow(() -> new AccountNotFoundException());
        Account receiver = accountRepository.findById(idReceiver).orElseThrow(() -> new AccountNotFoundException());
        /* Вычисляем новые балансы этих двух счетов */
        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);
        /* Изменяем счета отправителя и получателя уже в базе данных */
        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);
    }

    public Iterable<Account> getAllAccounts() {
        /* AccountRepository наследует этот метод от интерфейса CrudRepository, принадлежащего Spring Data */
        return accountRepository.findAll();
    }

   public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
   }
}
