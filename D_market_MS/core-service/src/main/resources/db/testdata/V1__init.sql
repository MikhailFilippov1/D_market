CREATE TABLE categories (id bigserial primary key, title VARCHAR(255));
INSERT INTO categories (title)
VALUES
('FOOD'),
 ('MATTER');

CREATE TABLE products (
        id                  bigserial primary key,
        title               VARCHAR(255),
        price               int,
        category_id bigint  references categories (id),
        created_at          timestamp default current_timestamp,
        updated_at          timestamp default current_timestamp);
INSERT INTO products (title, price, category_id)
VALUES
('BREAD', 45, 1),
 ('BUTTER', 245, 1),
  ('MILK', 75, 1),
   ('SALT', 5, 1),
    ('FISH', 545, 1),
    ('APPLE', 90, 1),
     ('ORANGE', 145, 1),
      ('POTATO', 28, 1),
       ('TOMATO', 75, 1),
        ('CUCUMBER', 80, 1);

CREATE TABLE orders (
        id                  bigserial primary key,
        username            VARCHAR(255) not null,
        total_price         int not null,
        address             VARCHAR(255),
        e_mail              VARCHAR(255),
        created_at          timestamp default current_timestamp,
        updated_at          timestamp default current_timestamp);

CREATE TABLE order_items (
        id                  bigserial primary key,
        product_id          bigint not null references products(id),
        order_id            bigint not null references orders(id),
        quantity            int not null,
        price_per_product   int not null,
        price               int not null,
        created_at          timestamp default current_timestamp,
        updated_at          timestamp default current_timestamp);