package ru.vlados.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Empty name")
    @Size(min = 2, max = 30, message = "Name between 2 and 30 characters")
    @Column(name = "name")
    private String name;
    @Min(value = 1,message = "Age must be more than 0")
    @Column(name = "age")
    private int age;
    @NotEmpty(message = "Empty email")
    @Email(message = "Email must be validated")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Order> orders;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, [0-9]{6}",message = "Your address should be in this form Country, City, code (6 digits)")
    @Column(name = "address")
    private String address;

    public Person() {
    }

    public Person(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
