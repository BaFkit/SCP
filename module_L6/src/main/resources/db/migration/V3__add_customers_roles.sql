DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_GUEST'),
       ('ROLE_SUPER_ADMIN');



DROP TABLE IF EXISTS customers_roles;
CREATE TABLE customers_roles
(
    id          SERIAL PRIMARY KEY,
    customer_id BIGINT,
    role_id     BIGINT
);
