package ru.vlados.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlados.spring.models.Order;
import ru.vlados.spring.models.Person;
import ru.vlados.spring.repositories.OrdersRepository;

import java.util.Calendar;
import java.util.Date;
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
    @Transactional
    public void save(Order order){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        order.setDate(calendar.getTime());
        ordersRepository.save(order);
    }

    @Transactional
    public void delete(int id){
        ordersRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Order order){
        order.setId(id);
        ordersRepository.save(order);
    }


}
