CREATE TABLE categories (id bigserial primary key, title VARCHAR(255));
INSERT INTO categories (title)
VALUES
('FOOD'),
 ('MATTER');

CREATE TABLE products (
        id                  bigserial primary key,
        title               VARCHAR(255),
        price               numeric(8,2),
        category_id bigint  references categories (id),
        created_at          timestamp default current_timestamp,
        updated_at          timestamp default current_timestamp);
INSERT INTO products (title, price, category_id)
VALUES
('BREAD', 45.22, 1),
 ('BUTTER', 245.00, 1),
  ('MILK', 75.00, 1),
   ('SALT', 5.00, 1),
    ('FISH', 545.00, 1),
    ('APPLE', 90.00, 1),
     ('ORANGE', 145.00, 1),
      ('POTATO', 28.00, 1),
       ('TOMATO', 75.00, 1),
        ('CUCUMBER', 80.00, 1),
        ('COFFEE', 555.00, 1),
         ('TEA', 145.00, 1),
          ('SUGAR', 48.00, 1),
           ('CACAO', 95.00, 1),
            ('JELEE', 57.00, 1),
            ('SPOON', 745.00, 2),
             ('CUP', 245.00, 2),
              ('PLATE', 75.00, 2),
               ('KNIFE', 312.00, 2),
                ('PAPER', 545.00, 2);

CREATE TABLE orders (
        id                  bigserial primary key,
        username            VARCHAR(255) not null,
        total_price         numeric(8,2) not null,
        address             VARCHAR(255),
        e_mail              VARCHAR(255),
        created_at          timestamp default current_timestamp,
        updated_at          timestamp default current_timestamp);

CREATE TABLE order_items (
        id                  bigserial primary key,
        product_id          bigint not null references products(id),
        order_id            bigint not null references orders(id),
        quantity            int not null,
        price_per_product   numeric(8,2) not null,
        price               numeric(8,2) not null,
        created_at          timestamp default current_timestamp,
        updated_at          timestamp default current_timestamp);