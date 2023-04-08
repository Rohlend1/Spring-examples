package ru.vlados.spring.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vlados.spring.models.Person;


import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    public List<Person> index(){
       return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person where id = ?",new BeanPropertyRowMapper<>(Person.class),id)
                .stream().findAny().orElse(null);
    }
    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)",person.getName(),
                person.getAge(),person.getEmail());
    }

    public void update(Person person,int id){
        jdbcTemplate.update("UPDATE Person SET name = ?, age = ?, email = ? WHERE id = ?"
                ,person.getName(),person.getAge(),person.getEmail(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE from person where id = ?",id);
    }
}
