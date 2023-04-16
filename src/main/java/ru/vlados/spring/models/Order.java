package ru.vlados.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.vlados.spring.services.CarConverter;



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
    @Convert(converter = CarConverter.class)
    private Car chosenCar;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @FutureOrPresent(message = "Non valid date")
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Person owner;

    @Transient
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Order(int price) {
        this.price = price;
    }

    public Order() {
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
        System.out.println("Ошибка в сеттере");
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
