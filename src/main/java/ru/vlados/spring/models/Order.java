package ru.vlados.spring.models;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Calendar;
import java.util.Date;

public class Order {
    private int id;
    @NotEmpty(message = "Empty car")
    @NotNull(message = "Car is null")
    @Size(min = 3,message = "Minimum length is 3")
    @Pattern(regexp = "Tesla|BMW|Mercedes|Lada|Porsche",message = "We have no this car")
    private String chosenCar;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @FutureOrPresent(message = "Non valid date")
    private Date date;

    public Order(int id, String chosenCar) {
        this.id = id;
        this.chosenCar = chosenCar;

    }

    public Order() {
        this.date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        calendar.add(Calendar.DATE, 7);
        this.date = calendar.getTime();
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

    public void setChosenCar(String chosenCar) {
        this.chosenCar = chosenCar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
