package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Павел on 16.04.2016.
 */
@ActiveProfiles(Profiles.JPA)
public class UserServiceJpaTest extends UserServiceTest {
}
