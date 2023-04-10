package ru.vlados.spring.dao;


import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vlados.spring.models.Person;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        jdbcTemplate.update("INSERT INTO Person(name,age,email, address) VALUES(?,?,?,?)",person.getName(),
                person.getAge(),person.getEmail(),person.getAddress());
    }

    public void update(Person person,int id){
        jdbcTemplate.update("UPDATE Person SET name = ?, age = ?, email = ?, address=? WHERE id = ?"
                ,person.getName(),person.getAge(),person.getEmail(),person.getAddress(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE from person where id = ?",id);
    }

    public void testMultipleUpdate(){
       List<Person> people = create1000People();
       long start = System.currentTimeMillis();
       for(Person person : people){
           jdbcTemplate.update("INSERT INTO Person(name,age,email,address) VALUES(?,?,?,?)"
                   ,person.getId(),person.getName(),person.getAge(),person.getEmail(),person.getAddress());
       }
        System.out.println(System.currentTimeMillis()-start);
    }

    public void testBatchUpdate(){
        List<Person> people = create1000People();
        long start = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO Person(name,age,email,address) VALUES(?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {

                ps.setString(1,people.get(i).getName());
                ps.setInt(2,people.get(i).getAge());
                ps.setString(3,people.get(i).getEmail());
                ps.setString(4,people.get(i).getAddress());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
        System.out.println(System.currentTimeMillis()-start);
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>(1000);
        for(int i = 0; i < 1000;i++){
            people.add(new Person(i,"name"+i,30,"test"+i+"@mail.ru","address"));
        }
        return people;
    }

    public Optional<Person> show(String email, int id){
        return jdbcTemplate.query("select * from Person where email = ? and id != ?",new PersonMapper(),email,id).stream().findAny();
    }

}
