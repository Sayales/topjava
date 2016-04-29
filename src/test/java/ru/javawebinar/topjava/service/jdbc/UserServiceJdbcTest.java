package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Павел on 16.04.2016.
 */
@ActiveProfiles(Profiles.JDBC)
public class UserServiceJdbcTest extends UserServiceTest {
}
