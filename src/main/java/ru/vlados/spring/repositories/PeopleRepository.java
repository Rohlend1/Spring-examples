package ru.vlados.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlados.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmailAndIdNot(String name,int id);

    List<Person> findByName(String name);

    List<Person> findByNameOrderByAgeDesc(String name);

    List<Person> findByNameStartingWith(String startingWith);
}
