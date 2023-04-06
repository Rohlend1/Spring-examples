package ru.vlados.spring.models;

public class Order {
    private int id;

    private String chosenCar;

    public Order(int id, String chosenCar) {
        this.id = id;
        this.chosenCar = chosenCar;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChosenCar() {
        return chosenCar;
    }


}
