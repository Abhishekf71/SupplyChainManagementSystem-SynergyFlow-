package com.example.miniamazon;

public class UserOrder {
    int id,price;
    String name,category,description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public UserOrder(int id, int price, String name, String category, String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.category = category;
        this.description = description;
    }
}
