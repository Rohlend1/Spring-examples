package ru.vlados.spring.dao;

import org.springframework.stereotype.Component;
import ru.vlados.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int count_people = 1;
    private final List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person(count_people++,"Tom"));
        people.add(new Person(count_people++,"Alex"));
        people.add(new Person(count_people++,"John"));
        people.add(new Person(count_people++,"Ivan"));
    }
    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        return people.stream().filter(e -> e.getId() == id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(count_people++);
        people.add(person);
    }

    public void update(Person person,int id){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
    }
    public void delete(int id){
        people.removeIf(e -> e.getId()==id);
    }
}
