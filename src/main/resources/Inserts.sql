
INSERT INTO worker(worker_salary, worker_name,worker_surname, worker_email, worker_phone)
VALUES  (10000, 'Шахрай', 'Ромчик', 'roma@gmail.ua', '5234546589'),
        (10100, 'Веснянко', 'Ромчик', 'vesna@gmail.ua', '8239871789'),
        (10200, 'Нищета','Ромчик', 'slava@gmail.ua', '923456457969'),
        (10300, 'Тесла', 'Ромчик','tesla@gmail.ua', '6295768989'),
        (10400, 'Тарасенко', 'Ромчик','taras@gmail.ua', '85753046789');



INSERT INTO customer(customer_name,customer_surname, customer_login, customer_password, customer_email, customer_phone, customer_address)
VALUES  ('Ромчик','Бойко','boyko','1234','boyko@gmail.ua','2146437635','Харьков'),
        ('Ромчик','Борисов','gorik','4321','gorik@gmail.ua','734966635','Львов'),
        ('Ромчик','Вірченко','deny','5499','deny@gmail.ua','609784637635','Москва'),
        ('Ромчик','Борщов','borsh','8711','borsh@gmail.ua','0164437635','Житомир'),
        ('Ромчик','Дорошко','trotyar','9364','trotyar@gmail.ua','6786437635','Одесса'),
        ('Ромчик','Дон','donchik','8550','donchik@gmail.ua','537636437635','Киев');


INSERT INTO provider (provider_name, provider_phone, provider_email)
VALUES  ('Makita','34265977','makita@gmail.ua'),
        ('Bosh','4378956977','bosh@gmail.ua'),
        ('Dewalt','8493248792','dewalt@gmail.ua'),
        ('AEG','235034e22','aeg@gmail.ua'),
        ('Stankoimport','1653242647','stankoimport@gmail.ua'),
        ('DniproM','4635731966','dniprom@gmail.ua');


INSERT INTO category(category_name)
VALUES  ('Инструменты'),
        ('Материалы'),
        ('Садовый инструмент'),
        ('Электроинструмент');


INSERT INTO product(product_name, product_price, product_weight, product_category, product_provider, product_quantity)
VALUES  ('Отвертка',100, '1',1,1,50),
        ('Молоток',150, '4',1,2,60),
        ('Ключ',160, '1',1,3,35),
        ('Рубанок',20, '3',1,4,40),
        ('Напильник',45, '2',1,5,20),

        ('Шифер',30, '2',2,4,300),
        ('Кирпич',50, '4',2,3,400),
        ('Доски',12, '5',2,2,500),
        ('Цемент',65, '3',2,2,350),
        ('Песок',89, '5',2,6,200),

        ('Газонокосилка',5900, '50',3,2,7),
        ('Бензопила',3590, '12',3,6,6),
        ('Мотоблок (бензин)',3780, '60',3,3,5),
        ('Мотоблок (дизель)',3980, '64',3,4,2),
        ('Электропила',3000, '10',3,1,8),


        ('Дрель',150, '5',4,1,5),
        ('УШМ',400, '8',4,2,5),
        ('Шурик',865, '7',4,4,5),
        ('Лобзик',783, '7',4,5,5),
        ('Паяльник',193, '9',4,6,5);


INSERT INTO admins(admin_login, admin_password)
VALUES  ('admin1', 'password'),
        ('admin2', 'password'),
        ('admin3', 'password'),
        ('admin4', 'password');








