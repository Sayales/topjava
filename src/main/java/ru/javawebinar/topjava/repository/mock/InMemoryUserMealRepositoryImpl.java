package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer,Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(e -> this.save(e, 1));
        UserMealsUtil.MEAL_LIST_2.forEach(e -> this.save(e,2));
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        if (repository.get(userId) == null)
            repository.put(userId, new ConcurrentHashMap<>());
        repository.get(userId).put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (!repository.get(userId).containsKey(id))
            return false;
        repository.get(userId).remove(id);
        return true;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        if (repository.get(userId) == null)
            repository.put(userId, new ConcurrentHashMap<>());
        return repository.get(userId).values()
                .stream()
                .sorted((m1, m2) -> m1.getDateTime().compareTo(m2.getDateTime()))
                .collect(Collectors.toList());
    }
}

