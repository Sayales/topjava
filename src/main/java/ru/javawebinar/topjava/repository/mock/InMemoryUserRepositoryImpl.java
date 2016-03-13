package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Павел on 10.03.2016.
 */
public class InMemoryUserRepositoryImpl implements UserRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        if (user.isNew())
            user.setId(counter.incrementAndGet());
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        if (repository.containsKey(id)) {
            repository.remove(id);
            return true;
        } else
            return false;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.values()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .get();
    }

    @Override
    public List<User> getAll() {
        return repository.values()
                .stream()
                .sorted( (u1, u2) -> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList());
    }
}
