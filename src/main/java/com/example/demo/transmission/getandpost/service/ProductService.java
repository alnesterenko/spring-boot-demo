package com.example.demo.transmission.getandpost.service;

import com.example.demo.transmission.getandpost.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/* В реальных приложениях так делать НЕЛЬЗЯ!
* Данные следует хранить в базах данных, чтобы не возникло состояние гонки! */

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }
}
