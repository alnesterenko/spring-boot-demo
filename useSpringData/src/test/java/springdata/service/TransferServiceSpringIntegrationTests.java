package springdata.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import springdata.model.Account;
import springdata.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/* Примечание:
* Аннотация @MockBean — это аннотация Spring Boot.
*  Если в вашем Spring-приложении нет Spring Boot, вы не сможете использовать @MockBean.
*  Но вы сможете применить тот же подход,
*  поставив перед классом конфигурации аннотацию @ExtendsWith(SpringExtension.class). */

@SpringBootTest
public class TransferServiceSpringIntegrationTests {
/* Создаем объект-заглушку, который входит в состав контекста Spring */
    @MockBean
    private AccountRepository accountRepository;
/* Внедряем реальный объект из контекста Spring, поведение которого хотим протестировать */
    @Autowired
    private TransferService transferService;

    @Test
    public void transferServiceTransferAmountTest() {
        /* Определяем все предпосылки для теста */
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId())).willReturn(Optional.of(sender));
        given(accountRepository.findById(receiver.getId())).willReturn(Optional.of(receiver));
        /* Вызываем тестируемый метод */
        transferService.transferMoney(1, 2, new BigDecimal(100));
        /* Проверяем, соответствует ли поведение тестируемого метода ожидаемому */
        verify(accountRepository).changeAmount(1, new BigDecimal(900));
        verify(accountRepository).changeAmount(2, new BigDecimal(1100));
    }
}
