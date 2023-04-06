package ru.vlados.spring.dao;

import org.springframework.stereotype.Component;
import ru.vlados.spring.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class OrderDAO {
    private static int count_orders;
    private final List<Order> orders = new ArrayList<>();
    private final Map<String,Integer> cars = new HashMap<>();

    {
        cars.put("Porsche",10_000_000);
        cars.put("Mercedes",15_000_000);
        cars.put("BMW",8_000_000);
        cars.put("Lada",1_000_000);
        cars.put("Tesla",3_000_000);
    }

    public Map<String, Integer> getCars() {
        return cars;
    }

    public void add(Order order){
        order.setId(count_orders++);
        orders.add(order);
    }
    public List<Order> getOrders(){
        return orders;
    }

    public Order getOrder(int id){
        for(Order order : orders){
            if(order.getId() == id) return order;
        }
        return null;
    }
}
