package com.example.miniamazon;

public class UserOrder {
    int id;
    String name,category,price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public UserOrder(int id, String name, String category, String price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
