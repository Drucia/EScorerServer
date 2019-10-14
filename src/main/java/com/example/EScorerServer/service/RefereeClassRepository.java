package com.example.EScorerServer.service;

import com.example.EScorerServer.model.RefereeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RefereeClassRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(RefereeClass refereeClass) {
        String sql = "insert into RefereeClasses (ShortKey, Name) values (?, ?)";
        jdbcTemplate.update(sql, refereeClass.getShortKey(), refereeClass.getName());
    }

    public List<RefereeClass> loadAll() {
        return jdbcTemplate.query("select * from RefereeClasses", (resultSet, i) -> {
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
