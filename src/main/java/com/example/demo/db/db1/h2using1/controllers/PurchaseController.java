package com.example.demo.db.db1.h2using1.controllers;

import com.example.demo.db.db1.h2using1.models.Purchase;
import com.example.demo.db.db1.h2using1.repositories.PurchaseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    /* Используя внедрение зависимости в конструктор, извлекаем объект репозитория из контекста Spring */
    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
    /* Создаем конечную точку, которую клиент может вызвать, чтобы сохранить в базе данных запись о покупке.
     Для сохранения данных, извлеченных действием контроллера из HTTP-запроса,
      используем метод storePurchase() репозитория */
    @PostMapping
    public void storePurchase(@RequestBody Purchase purchase) {
        purchaseRepository.storePurchase(purchase);
    }
    /* Создаем конечную точку, которую вызывает клиент, чтобы получить все записи из таблицы покупок.
     Действие контроллера извлекает данные из базы с помощью метода репозитория
      и затем возвращает их клиенту в теле HTTP-ответа */
    @GetMapping
    public List<Purchase> findPurchases() {
        return purchaseRepository.findAllPurchases();
    }
}
