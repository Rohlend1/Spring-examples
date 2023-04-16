package ru.vlados.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlados.spring.models.Order;
import ru.vlados.spring.models.Person;
import ru.vlados.spring.repositories.OrdersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Order findOne(int id){
       return ordersRepository.findById(id).orElse(null);
    }

    public List<Order> findAll(){
        return ordersRepository.findAll();
    }

    public List<Order> findByOwner(Person person){
        return ordersRepository.findByOwner(person);
    }




}
