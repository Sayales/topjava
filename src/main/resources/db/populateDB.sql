DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM user_meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO user_meals (user_id, datetime, description, calories) VALUES
  (100000, now(), 'Some descr', 500),
  (100000, now(), 'Some descr2', 600),
  (100001, now(), 'Admin food', 500),
  (100001, now(), 'Admin food2', 1000);
