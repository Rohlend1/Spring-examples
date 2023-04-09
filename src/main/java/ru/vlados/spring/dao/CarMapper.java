package ru.vlados.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.vlados.spring.models.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car>{
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getInt("price"));
        car.setId(rs.getInt("id"));
        return car;
    }
}
