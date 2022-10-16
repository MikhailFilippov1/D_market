DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial primary key, title VARCHAR(255), price int);
INSERT INTO products (title, price)
VALUES
('BREAD', 45),
 ('BUTTER', 245),
  ('MILK', 75),
   ('SALT', 5),
    ('MEET', 545),
    ('APPLE', 90),
     ('ORANGE', 145),
      ('POTATO', 28),
       ('TOMATO', 75),
        ('CUCUMBER', 80),
        ('COFFEE', 555),
         ('TEA', 145),
          ('SUGAR', 48),
           ('CACAO', 95),
            ('JELEE', 57),
            ('SPOON', 745),
             ('CUP', 245),
              ('PLATE', 75),
               ('KNIFE', 312),
                ('PAPER', 545);