package springdata.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import springdata.model.Account;

import java.math.BigDecimal;
import java.util.List;

/* Первое значение, принадлежащее к параметризованному типу, — это тип класса модели, представляющего таблицу.
 Второй — тип поля первичного ключа */
public interface AccountRepository extends CrudRepository<Account, Long> {

    /* Учтите, что имя параметра в запросе должно совпадать с именем параметра в методе.
     Между двоеточием (:) и именем параметра не должно быть пробела */
    @Query("SELECT * FROM account WHERE name = :name")
    List<Account> findAccountsByName(String name);

    /* Перед методами, которые определяют операции, изменяющие данные, ставится аннотация @Modifying */
    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void changeAmount(long id, BigDecimal amount);
}
