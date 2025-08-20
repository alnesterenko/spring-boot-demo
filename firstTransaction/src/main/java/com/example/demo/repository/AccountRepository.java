package com.example.demo.repository;

import com.example.demo.repository.mappers.AccountRowMapper;
import com.example.demo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/* Благодаря аннотации @Repository создаётся бин в контексте Spring,
 чтобы затем внедрить в класс сервиса, где он будет использован */
@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;
/* Путём внедрения зависимости в конструктор получаем объект JdbcTemplate для работы с базой данных */
    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Account findAccountById(long id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        /* С помощью метода queryForObject() объекта JdbcTemplate получаем информацию о счёте,
         отправляя в СУБД запрос SELECT.
        * Нам также понадобится RowMapper чтобы сообщить JdbcTemplate,
         как преобразовать строку -- результат запроса в объект модели.  */
        return jdbc.queryForObject(sql, new AccountRowMapper(), id);
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "UPDATE account SET amount = ? WHERE id = ?";
        /* С помощью метода update() объекта JdbcTemplate изменяем баланс счёта, отправляя в СУБД запрос UPDATE */
        jdbc.update(sql, amount, id);
    }

    /* Смотрим список всех аккаунтов */
    public List<Account> findAllAccounts() {
        String sql = "SELECT * FROM account";
        return jdbc.query(sql, new AccountRowMapper());
    }
}
