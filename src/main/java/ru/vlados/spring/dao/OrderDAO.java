package ru.vlados.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vlados.spring.models.Car;
import ru.vlados.spring.models.Order;

import java.util.*;


@Component
public class OrderDAO {

    private final SessionFactory sessionFactory;
    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public List<Car> getCars() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select c from Car as c", Car.class).getResultList();
    }
    @Transactional
    public void add(Order order){
        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
    }
    @Transactional(readOnly = true)
    public List<Order> getOrders(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Order as o",Order.class).getResultList();
    }
    @Transactional(readOnly = true)
    public Order getOrder(int id){
        return sessionFactory.getCurrentSession().get(Order.class,id);
    }
    @Transactional
    public void edit(Order order,int id){
        Session session = sessionFactory.getCurrentSession();
        session.merge(order);
//        jdbcTemplate.update("update orders set car = ?, date = ?, price = ? where id = ?",
//                order.getChosenCar(), order.getDate(), order.getPrice(),id);
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Order.class,id));
    }
}
