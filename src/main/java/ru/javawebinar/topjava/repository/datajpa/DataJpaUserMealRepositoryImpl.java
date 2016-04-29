package ru.javawebinar.topjava.repository.datajpa;

import org.hibernate.validator.internal.metadata.provider.ProgrammaticMetaDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
@Profile(Profiles.DATA_JPA)
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {

    @Autowired
    private ProxyUserMealRepository proxy;

    @Autowired
    private ProxyUserRepository userProxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User u = userProxy.getOne(userId);
        if (!userMeal.isNew() && get(userMeal.getId(), userId) == null)
            return null;
        userMeal.setUser(u);
        proxy.save(userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.get(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(userId, startDate, endDate);
    }
}
