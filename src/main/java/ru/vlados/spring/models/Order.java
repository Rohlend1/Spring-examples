package ru.vlados.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "Item")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "car",referencedColumnName = "id")
    private Car chosenCar;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @FutureOrPresent(message = "Non valid date")
    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Person owner;


    public Order(int price) {
        this.price = price;
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

    public Car getChosenCar() {
        return chosenCar;
    }

    public void setChosenCar(Car chosenCar) {
        this.chosenCar = chosenCar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
