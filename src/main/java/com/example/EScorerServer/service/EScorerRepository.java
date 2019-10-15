package com.example.EScorerServer.service;

import com.example.EScorerServer.model.RefereeClass;
import com.example.EScorerServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EScorerRepository implements IEScorerRepository.RefereeClassRep, IEScorerRepository.UserRep{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(RefereeClass refereeClass) {
        String sql = "insert into RefereeClasses (ShortKey, Name) values (?, ?)";
        jdbcTemplate.update(sql, refereeClass.getShortKey(), refereeClass.getName());
    }

    @Override
    public List<RefereeClass> loadAll() {
        return jdbcTemplate.query("select * from RefereeClasses", (resultSet, i) -> toRefereeClass(resultSet));
    }

    private RefereeClass toRefereeClass(ResultSet resultSet) throws SQLException {
        RefereeClass refereeClass = new RefereeClass();
        refereeClass.setShortKey(resultSet.getString("ShortKey"));
        refereeClass.setName(resultSet.getString("Name"));
        return refereeClass;
    }

    @Override
    public void save(User user) {
        String sql = "insert into Users (ID, Name, Surname, FullName, IsReferee, Certificate, " +
                "RefereeClassShortKey) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getSurname(), user.getFullName(), user.getReferee(),
                user.getCertificate(), user.getReferee());
    }

    @Override
    public User getUserById(String userId) {
        String sql = "select * from Users where Id LIKE ?";
        return (User) jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper(User.class));
    }
}
