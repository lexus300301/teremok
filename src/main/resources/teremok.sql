DROP TABLE IF EXISTS service_type CASCADE ;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS worker CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS service CASCADE;

DROP TABLE IF EXISTS admins;


CREATE TABLE worker (
                        worker_id SERIAL,
                        worker_salary integer not null,
                        worker_name varchar(50) not null,
                        worker_surname varchar(50) not null,
                        worker_phone varchar(25) not null,
                        PRIMARY KEY (worker_id)
);


INSERT INTO worker(worker_salary, worker_name,worker_surname,  worker_phone)
VALUES  (10000, 'Шахрай', 'Ромчик',  '5234546589'),
        (10100, 'Веснянко', 'Ромчик',  '8239871789'),
        (10200, 'Нищета','Ромчик',  '923456457969'),
        (10300, 'Тесла', 'Ромчик', '6295768989'),
        (10400, 'Тарасенко', 'Ромчик', '85753046789');



CREATE TABLE customer (
                          customer_id SERIAL,
                          customer_fullname varchar(100),
                          customer_phone varchar(25),
                          customer_address varchar(25),
                          PRIMARY KEY (customer_id)
);

INSERT INTO customer(customer_fullname,   customer_phone, customer_address)
VALUES  ('Ромчик Бойко','2146437635','Харьков'),
        ('Ромчик Борщов','2146437635','Харьков'),
        ('Ромчик Кухарчук','2146437635','Харьков'),
        ('Ромчик ЗакладнойЦарь','2146437635','Харьков');



CREATE TABLE service_type (
                              service_type_id SERIAL,
                              service_type_name varchar(100),
                              PRIMARY KEY (service_type_id)
);

INSERT INTO service_type(service_type_name)
VALUES  ('Зал'),
        ('Бассейн'),
        ('Йога'),
        ('Бокс');





CREATE TABLE service (
                         service_id SERIAL,
                         service_price varchar(50) not null,
                         service_s_type integer not null,
                         PRIMARY KEY (service_id),
                         FOREIGN KEY (service_s_type) REFERENCES service_type(service_type_id) ON DELETE RESTRICT

);

INSERT INTO service( service_price, service_s_type)
VALUES  (100, 1),
        (500, 2),
        (10000,3),
        (1000000,4);



CREATE TABLE orders (
                        order_id SERIAL,
                        order_customer integer  not null,
                        order_worker integer not null,
                        order_service integer,
                        order_duration integer,
                        order_price integer,
                        PRIMARY KEY (order_id),
                        FOREIGN KEY (order_service) REFERENCES service(service_id) ON DELETE RESTRICT
);





CREATE TABLE admins (
                        admin_id SERIAL,
                        admin_login varchar(10) not null ,
                        admin_password varchar(50),
                        PRIMARY KEY (admin_id)
);








