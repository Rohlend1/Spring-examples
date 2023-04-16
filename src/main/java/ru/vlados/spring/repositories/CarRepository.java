package ru.vlados.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlados.spring.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findByModel(String name);

}
