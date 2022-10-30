DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE user_roles
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    role    VARCHAR NOT NULL
);
CREATE UNIQUE INDEX roles_idx ON user_roles(role);

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    role_id          INTEGER,
    calories_per_day INTEGER             DEFAULT 2000  NOT NULL,
    FOREIGN KEY (role_id) REFERENCES user_roles (id)  ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE meals
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id     INTEGER   NOT NULL,
    date        DATE      NOT NULL,
    description TEXT      NOT NULL,
    calories    INT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
