drop table if exists staff;

create table staff
(
    id integer primary key auto_increment,
    email varchar(50) not null,
    name varchar(30) not null,
    password varchar(50) not null,
    token varchar(50)
);

alter table pet_owner
add token varchar(50);

insert into staff(email, name, password)
VALUES ('staff1@staff.com', 'Staff 1', 'staff_1'),
       ('staff2@staff.com', 'Staff 2', 'staff_2');