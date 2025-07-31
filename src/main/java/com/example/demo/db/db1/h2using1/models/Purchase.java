package com.example.demo.db.db1.h2using1.models;

import java.math.BigDecimal;

public class Purchase {

    private int id;

    private String product;
/* Чтобы не потерять десятичную точность при выполнении различных операций,
 для хранения значений с плавающей точкой применяйте BigDecimal вместо double или float. */
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
