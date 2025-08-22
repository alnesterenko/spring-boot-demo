package springdata.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springdata.exception.AccountNotFoundException;
import springdata.model.Account;
import springdata.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/* С помощью данной аннотации разрешаем использование аннотаций @Mock и @InjectMocks */
@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    /* С помощью аннотации @Mock создаем объект-заглушку и внедряем его в поле тестового класса,
     перед которым стоит эта аннотация */
    @Mock
    private AccountRepository accountRepository;

    /* С помощью аннотации @InjectMocks создаем тестируемый объект и внедряем его в поле,
     перед которым стоит эта аннотация */
    /* То есть, в transferService внедряем accountRepository */
    @InjectMocks
    private TransferService transferService;

    @Test
    @DisplayName("Test the amount is transferred from one account to another if no exception occurs.")
    public void moneyTransferHappyFlow() {

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

    @Test
    public void moneyTransferDestinationAccountNotFoundFlow() {

        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));
        /* Управляя заглушкой AccountRepository, мы делаем так, чтобы метод findById(),
         вызванный для счета получателя, возвращал пустой объект Optional */
        given(accountRepository.findById(2L)).willReturn(Optional.empty());
        /* Мы предполагаем, что для данного варианта выполнения метод должен выбрасывать исключение AccountNotFoundException */
        assertThrows(AccountNotFoundException.class, () -> transferService.transferMoney(1, 2, new BigDecimal(100)));

        /* Используем метод verify() с условием never() для уверенности, что метод changeAmount() не вызывается */
        verify(accountRepository, never()).changeAmount(anyLong(), any());
    }
}