package springdata.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springdata.model.Account;
import springdata.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TransferServiceTest {

    @Test
    @DisplayName("Test the amount is transferred from one account to another if no exception occurs.")
    public void moneyTransferHappyFlow() {
        /* С помощью метода Mockito mock() создаем экземпляр-заглушку для объекта AccountRepository */
        AccountRepository accountRepository = mock(AccountRepository.class);
        /* Создаем экземпляр объекта TransferService, метод которого хотим протестировать.
         Вместо настоящего экземпляра AccountRepository создаем объект, играющий роль его заглушки.
          Таким образом мы заменяем зависимость на объект, которым можем управлять */
        TransferService transferService = new TransferService(accountRepository);

        /* Создаем экземпляры Account для отправителя и получателя,
         где хранится информация об их счетах — предполагается,
          что в действительности приложение получает ее из базы данных */
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        /* Управляемый нами метод findById(), получая ID счета отправителя, возвращает экземпляр этого счета.
         Данную строку следует читать так:
          «Если вызвать findById() и передать ему ID счета отправителя в виде параметра,
           этот метод вернет экземпляр счета отправителя» */
        given(accountRepository.findById(sender.getId())).willReturn(Optional.of(sender));
        given(accountRepository.findById(receiver.getId())).willReturn(Optional.of(receiver));

        /* Вызываем метод, который хотим протестировать, передавая ему ID отправителя, ID получателя и сумму перевода */
        transferService.transferMoney(
                sender.getId(),
                receiver.getId(),
                new BigDecimal(100));

        /* Проверяем, что метод changeAmount() из AccountRepository вызван с ожидаемыми параметрами */
        verify(accountRepository).changeAmount(1, new BigDecimal(900));
        verify(accountRepository).changeAmount(2, new BigDecimal(1100));
    }

}