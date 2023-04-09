package ru.vlados.spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vlados.spring.models.Car;
import ru.vlados.spring.models.Order;

import java.util.*;


@Component
public class OrderDAO {

    private final JdbcTemplate jdbcTemplate;

    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> getCars() {
        return jdbcTemplate.query("select * from cars",new CarMapper());
    }

    public void add(Order order){
        Car orderedCar = jdbcTemplate.query("select * from cars where model = ?"
                ,new CarMapper(),order.getChosenCar()).stream().findAny().orElse(null);

        jdbcTemplate.update("INSERT into orders(car,user_id,price,date) values(?,?,?,?)"
                , Objects.requireNonNull(orderedCar).getId(),1
                ,orderedCar.getPrice()
                ,order.getDate());
    }
    public List<Order> getOrders(){
        return jdbcTemplate.query("select * from Orders",new OrderMapper(jdbcTemplate));
    }

    public Order getOrder(int id){
        return jdbcTemplate.query("select * from Orders where id = ?",new OrderMapper(jdbcTemplate),id).stream().findAny().orElse(null);
    }
    public void edit(Order order,int id){
        jdbcTemplate.update("update orders set car = ?, date = ?, price = ? where id = ?",
                order.getChosenCar(), order.getDate(), order.getPrice(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("delete from orders where id = ?",id);
    }
}
