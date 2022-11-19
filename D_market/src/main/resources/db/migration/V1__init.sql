CREATE TABLE IF NOT EXISTS categories (id bigserial primary key, title VARCHAR(255));
INSERT INTO categories (title)
VALUES
('FOOD'),
 ('MATTER');

CREATE TABLE IF NOT EXISTS products (id bigserial primary key, title VARCHAR(255), price int, category_id bigint references categories (id));
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
        ('CUCUMBER', 80, 1),
        ('COFFEE', 555, 1),
         ('TEA', 145, 1),
          ('SUGAR', 48, 1),
           ('CACAO', 95, 1),
            ('JELEE', 57, 1),
            ('SPOON', 745, 2),
             ('CUP', 245, 2),
              ('PLATE', 75, 2),
               ('KNIFE', 312, 2),
                ('PAPER', 545, 2);

create table users (
  id                    bigserial primary key,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  created_at            timestamp default current_timestamp,
  updated_at            timestamp default current_timestamp
);

create table roles (
  id                    serial primary key,
  name                  varchar(50) not null,
  created_at            timestamp default current_timestamp,
  updated_at            timestamp default current_timestamp
);

CREATE TABLE users_roles (
  user_id               bigint not null references users (id),
  role_id               bigint not null references roles (id),
  primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('READ_PROFILE');

insert into users (username, password, email)
values
('mike', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'mike@gmail.com'),
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(2, 3);