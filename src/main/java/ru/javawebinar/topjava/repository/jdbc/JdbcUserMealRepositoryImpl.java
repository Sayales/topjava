package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {

    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUserMeal;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertUserMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("USER_MEALS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("user_id", userId)
                .addValue("datetime", Timestamp.valueOf(userMeal.getDateTime()))
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories());
        if (userMeal.isNew()) {
            Number key = insertUserMeal.execute(map);
            userMeal.setId(key.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE user_meals SET user_id=:user_id, datetime=:datetime, description=:description, calories=:calories",
                    map);
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("user_id", userId);
        return (namedParameterJdbcTemplate.update("DELETE FROM user_meals WHERE id=:id AND user_id=:user_id", map) != 0);
    }

    @Override
    public UserMeal get(int id, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("user_id", userId);
        List<UserMeal> userMeals = jdbcTemplate.query("SELECT * FROM user_meals WHERE id=? AND user_id=?", new UserMealRowMapper(userId), id, userId);
        return DataAccessUtils.singleResult(userMeals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        List<UserMeal> result = jdbcTemplate.query("SELECT * FROM user_meals WHERE user_id=? ORDER BY datetime DESC",new UserMealRowMapper(userId), userId);
        return result;
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<UserMeal> result = jdbcTemplate.query("SELECT * FROM user_meals WHERE datetime > ? AND datetime < ? ORDER BY datetime DESC", ROW_MAPPER, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
        return result;
    }
    private class UserMealRowMapper implements RowMapper<UserMeal> {

        private int userId;

        public UserMealRowMapper(int userId) {
            this.userId = userId;
        }

        @Override
        public UserMeal mapRow(ResultSet resultSet, int i) throws SQLException {
            if (resultSet.getInt("user_id") == userId) {
                UserMeal userMeal = new UserMeal();
                userMeal.setId(resultSet.getInt("id"));
                userMeal.setDateTime(resultSet.getTimestamp("datetime").toLocalDateTime());
                userMeal.setCalories(resultSet.getInt("calories"));
                userMeal.setDescription(resultSet.getString("description"));
                return userMeal;
            } else
                return null;
        }
    }
}
