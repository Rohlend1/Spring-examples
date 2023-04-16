package ru.vlados.spring.services;

import jakarta.persistence.AttributeConverter;

import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vlados.spring.models.Car;

@Converter
@Component
public class CarConverter implements AttributeConverter<String, Car> {

    private final CarService carService;
    @Autowired
    public CarConverter(CarService carService) {
        this.carService = carService;
    }

    @Override
    public Car convertToDatabaseColumn(String s) {
        return carService.findByName(s);
    }

    @Override
    public String convertToEntityAttribute(Car car) {
        return car.toString();
    }
}
