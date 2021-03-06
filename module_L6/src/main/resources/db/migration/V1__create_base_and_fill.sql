DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id    serial PRIMARY KEY,
    title VARCHAR(255),
    cost  numeric(8, 2)
);
INSERT INTO products (title, cost)
VALUES ('iPhone', 799.00),
       ('iPad', 1100.00),
       ('AppleWatch', 399.00),
       ('MacBook', 1899.00);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers
(
    id   serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
INSERT INTO customers (name, login, password)
VALUES ('Ivan', 'admin', 'admin'),
       ('Petr', 'user', 'user'),
       ('Vasy', 'manager', 'manager'),
       ('Guest', 'guest', 'guest'),
       ('Anton', 'super', 'super');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
    id            serial PRIMARY KEY,
    customer_id   serial,
    customer_name VARCHAR(255),
    product_id    serial,
    product_title VARCHAR(255),
    order_cost    numeric(8, 2)
);