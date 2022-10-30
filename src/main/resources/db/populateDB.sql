DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO user_roles (role)
VALUES ('USER'),
       ('ADMIN');

INSERT INTO users (name, email, role_id,  calories_per_day)
VALUES ('User1', 'user1@yandex.ru', 100000,  2005),
       ('User2', 'user2@yandex.ru', 100000, 1800),
       ('User3', 'user3@yandex.ru', 100000, 2020),
       ('Admin', 'admin@gmail.com', 100001, 1900);


INSERT INTO meals (date, description, calories, user_id)
VALUES ('2022-01-30', 'Завтрак', 500, 100002),
       ('2022-01-30', 'Обед', 1000, 100002),
       ('2022-01-30', 'Ужин', 500, 100002),
       ('2022-01-31', 'Завтрак', 500, 100003),
       ('2022-01-31', 'Обед', 1000, 100003),
       ('2022-01-31', 'Ужин', 510, 100003),
       ('2022-01-31', 'Завтрак', 450, 100004),
       ('2022-01-31', 'Обед', 900, 100004),
       ('2022-01-31', 'Ужин', 516, 100004),
       ('2022-01-31', 'Админ ланч', 510, 100005),
       ('2022-01-31', 'Админ ужин', 1500, 100005);