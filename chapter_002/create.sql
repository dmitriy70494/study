
	create database system_of_orders;

        CREATE TABLE role(
        id serial primary key,
        name character varying(2000)
        );

        CREATE TABLE rules(
        id serial primary key,
        name character varying(2000)
        );

        CREATE TABLE role_rules(
        id_role integer references role(id),
        id_rules integer references rules(id)
        );

        CREATE TABLE users(
        id serial primary key,
        name character varying(2000),
        login character varying(2000),
        password character varying(2000),
        role_id integer references role(id)
        );

        CREATE TABLE category(
        id serial primary key,
        name character varying(200)
        );

        CREATE TABLE state(
        id serial primary key,
        name character varying(200)
        );

        CREATE TABLE item(
        id serial primary key,
        description text,
        id_user integer references users(id),
        id_category integer references category(id),
        id_state integer references state(id)
        );

        CREATE TABLE comments(
        id serial primary key,
        description text,
        id_item integer references item(id)
        );

        CREATE TABLE attachs(
        id serial primary key,
        filename character varying(2000),
        id_item integer references item(id)
        );

        INSERT INTO role(name)
        VALUES('Administrator');

        INSERT INTO role(name)
        VALUES('Seller');

        INSERT INTO role(name)
        VALUES('Customer');

        SELECT * FROM role;

        INSERT INTO rules(name)
        VALUES('Make order');

        INSERT INTO rules(name)
        VALUES('Read status his orders');

        INSERT INTO rules(name)
        VALUES('Edit order');

        INSERT INTO rules(name)
        VALUES('Cancel order');

        SELECT * FROM rules;

        INSERT INTO role_rules(id_role,id_rules)VALUES
        (1,2),
        (2,1),
        (2,3),
        (3,4),
        (3,3),
        (3,2),
        (3,1);

        SELECT * FROM role_rules;

        INSERT INTO user(name,login,password,role_id)VALUES
        ('Dima','dmitriy','123',1),
        ('Lena','selena','1234',2),
        ('Andrey','andrey','12345',3);

        INSERT INTO category(name)VALUES
        ('sale'),
        ('purcher');

        SELECT * FROM category;

        INSERT INTO state(name)VALUES
        ('finished'),
        ('cancel'),
        ('processed');

        SELECT * FROM state;

        INSERT INTO item(description,id_user,id_category,id_state)VALUES
        ('Покупка телевизора самсунг под заказ 3 дня',11,12,16),
        ('Продажа холодильника',12,11,18);

        SELECT * FROM item;

        INSERT INTO comments(description,id_item)VALUES
        ('bla-bla',17),
        ('что?',17),
        ('Сколько стоит?',18),
        ('Его уже купили',18);

        INSERT INTO attachs(filename,id_item)VALUES
        ('test.txt',17),
        ('dogovor.doc',17),
        ('qw.jpeg',18);

        SELECT * FROM comments;

        SELECT * FROM attachs;

		