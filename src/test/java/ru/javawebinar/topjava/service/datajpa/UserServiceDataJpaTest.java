package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Павел on 16.04.2016.
 */
@ActiveProfiles(Profiles.DATA_JPA)
public class UserServiceDataJpaTest extends UserServiceTest {
}
