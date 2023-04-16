package ru.vlados.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlados.spring.models.Car;
import ru.vlados.spring.repositories.CarRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Car findById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car findByName(String name){
        return carRepository.findByModel(name);
    }

}
