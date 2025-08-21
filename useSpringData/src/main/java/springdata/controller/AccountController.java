package springdata.controller;

import org.springframework.web.bind.annotation.*;
import springdata.dto.TransferRequest;
import springdata.model.Account;
import springdata.service.TransferService;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(
            /* Извлекаем из тела HTTP-запроса идентификаторы счетов отправителя и получателя, а также сумму перевода */
            @RequestBody TransferRequest request) {
        /* Вызываем сервис для выполнения сценария использования «перевод денег» */
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
            /* Используем дополнительный параметр запроса, чтобы передать имя владельца,
             информацию о счете которого нужно получить */
            @RequestParam(required = false) String name) {
        /* Если мы не передадим имя в виде дополнительного параметра, вернется информация обо всех счетах */
        if (name == null) {
            return transferService.getAllAccounts();
            /* Если среди параметров запроса есть имя, мы получим только информацию о счете пользователя с этим именем */
        } else {
            return transferService.findAccountsByName(name);
        }
    }
}
