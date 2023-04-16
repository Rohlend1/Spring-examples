package ru.vlados.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlados.spring.models.Order;
import ru.vlados.spring.models.Person;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Integer>{

    List<Order> findByOwner(Person owner);
}
