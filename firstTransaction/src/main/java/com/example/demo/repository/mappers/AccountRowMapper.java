package com.example.demo.repository.mappers;

import com.example.demo.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Реализуем контракт RowMapper и передаём ему в качестве параметризованного типа класс модели,
* в которую будет преобразована строка результата. */
public class AccountRowMapper implements RowMapper<Account> {

    /* Реализуем метод mapRow(), получающий в качестве параметра строку результата
     (в виде объекта ResultSet) и возвращающий экземпляр Account,
      в который преобразуется текущая строка */
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setAmount(resultSet.getBigDecimal("amount"));
        /* Возвращаем экземпляр Account в который были преобразованы результаты запроса. */
        return account;
    }
}
