-- ${flyway:timestamp}

DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS pet_owner;
DROP TABLE IF EXISTS address;

CREATE TABLE address
(
    id integer primary key auto_increment,
    house_number varchar(5) not null,
    street varchar(20) not null,
    town varchar(20) not null,
    postcode varchar(8) not null
);

CREATE TABLE pet_owner
(
    id integer primary key auto_increment,
    address integer not null,
    email varchar(50) not null,
    name varchar(30) not null,
    password varchar(50) not null,
    telephone varchar(15),
    token varchar(50),
    foreign key (address) references address(id)
);

CREATE TABLE pet
(
    id integer primary key auto_increment,
    type_of_animal varchar(15) not null,
    name varchar(20) not null,
    date_of_birth date,
    owner integer not null,
    foreign key (owner) references pet_owner(id)
);

CREATE TABLE staff
(
    id integer primary key auto_increment,
    email varchar(50) not null,
    name varchar(30) not null,
    password varchar(50) not null,
    token varchar(50)
);

INSERT INTO address (house_number, street, town, postcode)
VALUES('5', 'High Street', 'Toon Town', 'TT1 0AA'),
       ('3a', 'Horizon Road', 'Vile Village', 'TT12 6PD');

INSERT INTO pet_owner (address, email, name, password, telephone)
VALUES (1, 'po1@tt.com', 'Pet owner 1', 'pet_owner_1', '+44 1234 123456'),
       (1, 'po2@tt.com', 'Pet owner 2', 'pet_owner_2', '+44 2323 345678'),
       (2, 'po3@po.com', 'Pet owner 3', 'pet_owner_3', '+44 7654 987654');

INSERT INTO pet (type_of_animal, name, date_of_birth, owner)
VALUES ('Cat', 'Fluffy', '2018-05-14', 1),
       ('Dog', 'Growler', '2018-03-23', 1),
       ('Parrot', 'Squawker', '2008-09-15', 2),
       ('Corn snake', 'Slider', '2021-12-31', 3);

INSERT INTO staff(email, name, password)
VALUES ('staff1@staff.com', 'Staff 1', 'staff_1'),
       ('staff2@staff.com', 'Staff 2', 'staff_2');
