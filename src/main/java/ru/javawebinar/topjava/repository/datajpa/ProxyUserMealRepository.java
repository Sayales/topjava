package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Павел on 16.04.2016.
 */

@Transactional(readOnly = true)
@Profile(Profiles.DATA_JPA)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {


    @Override
    @Transactional
    UserMeal save(UserMeal userMeal);

    @Query("SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    UserMeal get(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    List<UserMeal> findByUserIdOrderByDateTimeDesc(int userId);


    List<UserMeal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId,LocalDateTime startDate, LocalDateTime endDate);
}
