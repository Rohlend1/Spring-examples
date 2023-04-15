package ru.vlados.spring.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vlados.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person as p",Person.class).getResultList();
    }
    @Transactional(readOnly = true)
    public Person show(int id){
        return sessionFactory.getCurrentSession().get(Person.class,id);
    }

    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(Person person,int id){
        Session session = sessionFactory.getCurrentSession();
        session.merge(person);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(show(id));
    }
    @Transactional
    public Optional<Person> show(String email, int id){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person as p where p.email = :email and p.id != :id", Person.class)
                .setParameter("email",email).setParameter("id",id).getResultList().stream().findAny();
    }

}
