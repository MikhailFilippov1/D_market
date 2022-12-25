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