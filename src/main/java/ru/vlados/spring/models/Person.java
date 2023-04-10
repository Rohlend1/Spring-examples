package ru.vlados.spring.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Empty name")
    @Size(min = 2, max = 30, message = "Name between 2 and 30 characters")
    private String name;
    @Min(value = 1,message = "Age must be more than 0")
    private int age;
    @NotEmpty(message = "Empty email")
    @Email(message = "Email must be validated")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, [0-9]{6}",message = "Your address should be in this form Country, City, code (6 digits)")
    private String address;

    public Person() {
    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
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
