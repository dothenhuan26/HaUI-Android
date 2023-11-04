package com.example.bai86_49_dothenhuan_2021600381;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Product {
    private String id;
    private String name;
    private String category;
    private int amount;

    public Product() {
    }

    public Product(String id, String name, String category, int amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return this.id + "-" + this.name + "-" + this.category + "-" + this.amount;
    }
}
