package com.example.EScorerServer.service;

import com.example.EScorerServer.model.RefereeClass;
import com.example.EScorerServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "insert into Users (ID, Name, Surname, FullName, IsReferee, Certificate, " +
                "RefereeClassShortKey) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getSurname(), user.getFullName(), user.getReferee(),
                user.getCertificate(), user.getReferee());
    }

    public List<RefereeClass> loadAll() {
        return jdbcTemplate.query("select * from Users", (resultSet, i) -> {
            return toRefereeClass(resultSet);
        });
    }

    private RefereeClass toRefereeClass(ResultSet resultSet) throws SQLException {
        RefereeClass refereeClass = new RefereeClass();
        refereeClass.setShortKey(resultSet.getString("ShortKey"));
        refereeClass.setName(resultSet.getString("Name"));
        return refereeClass;
    }
}
