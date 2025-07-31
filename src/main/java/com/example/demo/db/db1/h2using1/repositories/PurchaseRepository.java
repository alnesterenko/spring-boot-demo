package com.example.demo.db.db1.h2using1.repositories;

import com.example.demo.db.db1.h2using1.models.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/* Добавляем бин этого типа в контекст Spring с помощью стереотипной аннотации @Repository */
@Repository
public class PurchaseRepository {

    private final JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(PurchaseRepository.class.getName());

    /* Используем внедрение в конструкторе, чтобы получить экземпляр JdbcTemplate из контекста приложения */
    public PurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* В качестве параметра метод принимает данные, которые нужно сохранить */
    public void storePurchase(Purchase purchase) {
        /* Запрос представляет собой строку, в которой вместо значений параметров стоят вопросительные знаки (?).
         Вместо ID ставим NULL, так как СУБД сама генерирует значение для этого столбца */
        /* !!! Чтобы небыло конфликтов из-за автоинкримента,
         нужно указать передаваемые поля и убрать значение для id !!! */
        String sql = "INSERT INTO purchase(product, price) VALUES (?, ?)";
        /* Метод update() экземпляра JdbcTemplate посылает запрос на сервер баз данных.
         Первый параметр метода — сам запрос, а остальные — значения параметров запроса.
          Эти значения в указанной последовательности подставляются в запрос вместо вопросительных знаков */
        int response = jdbcTemplate.update(
                sql,
                purchase.getProduct(),
                purchase.getPrice());

        logger.info("Добавлено строк " + response);
    }

    /* Метод возвращает записи, полученные из базы данных, в виде списка объектов Purchase */
    public List<Purchase> findAllPurchases() {

        /* Определяем запрос SELECT для получения всех записей из таблицы покупок */
        String sql = "SELECT * FROM purchase";
        /* Создаем объект RowMapper, который сообщает JdbcTemplate, как преобразовать строку,
         полученную из базы данных, в объект Purchase.
          Параметр r лямбда-выражения соответствует ResultSet (данным, полученным из базы),
           а параметр i — целое число, показывающее номер строки */
        RowMapper<Purchase> purchaseRowMapper = (r, i) -> {
            /* Заносим данные в экземпляр Purchase.
             JdbcTemplate будет выполнять эту логику для каждой строки из набора результатов */
            Purchase rowObject = new Purchase();
            rowObject.setId(r.getInt("id"));
            rowObject.setProduct(r.getString("product"));
            rowObject.setPrice(r.getBigDecimal("price"));
            return rowObject;
        };
        /* Отправляем запрос SELECT, используя метод query(), и передаем объект преобразователя строк,
         чтобы JdbcTemplate знал, как преобразовать полученные данные в объекты Purchase */
        return jdbcTemplate.query(sql, purchaseRowMapper);
    }
}
