package ru.vlados.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlados.spring.models.Person;
import ru.vlados.spring.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

   private final PeopleRepository peopleRepository;

   @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
       return peopleRepository.findById(id).orElse(null);
    }

    public Optional<Person> findOne(String email,int id){
       return peopleRepository.findByEmailAndIdNot(email,id);
    }

    @Transactional
    public void save(Person person){
       person.setCreatedAt(new Date());
       peopleRepository.save(person);
    }

    @Transactional
    public void update(int id,Person person){
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Person> findByName(String name){
       return peopleRepository.findByName(name);
    }

    public List<Person> findByNameOrderByAgeDesc(String name){
        return peopleRepository.findByNameOrderByAgeDesc(name);
    }

    public List<Person> findByNameStartingWith(String startingWith){
       return peopleRepository.findByNameStartingWith(startingWith);
    }
    public void test(){
        System.out.println("Inside hibernate transaction");
    }
}
