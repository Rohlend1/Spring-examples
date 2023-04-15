//package ru.vlados.spring.dao;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import ru.vlados.spring.models.Order;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Objects;
//
//public class OrderMapper implements RowMapper<Order> {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public OrderMapper(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//        Order order = new Order();
//        order.setId(rs.getInt("id"));
//        order.setDate(rs.getDate("date"));
//        order.setChosenCar(
//                Objects.requireNonNull(jdbcTemplate.query("select * from cars where id = ?"
//                        , new CarMapper()
//                        , rs.getInt("car")).stream().findAny().orElse(null)).getModel());
//        order.setPrice(rs.getInt("price"));
//
//        return order;
//    }
//}
