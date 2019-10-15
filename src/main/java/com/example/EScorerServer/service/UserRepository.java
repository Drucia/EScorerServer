//package com.example.EScorerServer.service;
//
//import com.example.EScorerServer.model.RefereeClass;
//import com.example.EScorerServer.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class UserRepository {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public void save(User user) {
//        String sql = "insert into Users (ID, Name, Surname, FullName, IsReferee, Certificate, " +
//                "RefereeClassShortKey) values (?, ?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getSurname(), user.getFullName(), user.getReferee(),
//                user.getCertificate(), user.getReferee());
//    }
//
//    public List<User> loadAll() {
//        return jdbcTemplate.query("select * from Users", (resultSet, i) -> toUser(resultSet));
//    }
//
//    public User getUserById(String userId){
//        String sql = "select * from Users where Id = ?";
//        return (User) jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper(User.class));
//    }
//
//    private User toUser(ResultSet resultSet) throws SQLException {
//        User user = new User();
//        user.setId(resultSet.getString("Id"));
//        user.setName(resultSet.getString("Name"));
//        user.setSurname(resultSet.getString("Surname"));
//        user.setReferee(resultSet.getBoolean("IsReferee"));
//        user.setCertificate(resultSet.getString("Certificate"));
//        return user;
//    }
//}
