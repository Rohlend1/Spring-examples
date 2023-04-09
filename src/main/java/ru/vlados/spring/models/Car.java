package ru.vlados.spring.models;

public class Car {
    private int price;

    private int id;
    private String model;

    public Car(int price, int id, String model) {
        this.price = price;
        this.id = id;
        this.model = model;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
