package ru.vlados.spring.dao;

import org.springframework.stereotype.Component;
import ru.vlados.spring.models.Order;

import java.util.*;


@Component
public class OrderDAO {
    private static int count_orders =1;
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
    public void edit(Order order,int id){
        Order updatedOrder = getOrder(id);
        updatedOrder.setChosenCar(order.getChosenCar());
        updatedOrder.setDate(order.getDate());
    }
    public void delete(int id){
        orders.removeIf(e -> e.getId()==id);
    }
}
