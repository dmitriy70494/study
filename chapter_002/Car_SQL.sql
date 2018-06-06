--  если не ставить ; переносит на следующую строку и продолжает ввод. если ввести команду ввод запроса не прекращается, его не сбросить пока не ввести ; нужно посмотреть варианты

create database car_system;

--  \c car_system команда входит в конкретную базу данных 

CREATE TABLE Bodywork (
id serial primary key,
name character varying (120)
);

CREATE TABLE Motor (
id serial primary key,
name character varying (120),
model integer
);

CREATE TABLE Transmission (
id serial primary key,
name character varying (120)
);

CREATE TABLE Car (
id serial primary key,
name character varying (120),
id_bodywork integer references Bodywork(id),
id_motor integer references Motor(id),
id_transmission integer references Transmission(id)
);

-- \dt - выводит список таблиц базы

INSERT INTO Bodywork (name) VALUES
('sedan'),
('universal'),
('hatchback');

INSERT INTO Motor (name, model) VALUES
('Volkswagen W8', 1),
('Lancia Delta S4', 1),
('Porsche Fuhrmann', 1);

ALTER TABLE Motor DROP COLUMN model CASCADE;

INSERT INTO Transmission (name) VALUES
('manual gearbox'),
('automatic transmission');

SELECT * FROM bodywork;

SELECT * FROM motor;

SELECT * FROM transmission;

INSERT INTO Car(name, id_bodywork, id_motor, id_transmission) VALUES
('car', 1, 3, 2);

SELECT a.id, a.name, b.name AS bodywork, c.name AS motor, d.name AS transmission FROM 
((car AS a INNER JOIN Bodywork AS b ON a.id_bodywork = b.id) 
INNER JOIN Motor AS c ON a.id_motor = c.id) 
INNER JOIN Transmission AS d ON a.id_transmission = d.id;

SELECT a.name AS bodywork FROM Bodywork As a LEFT JOIN Car AS b ON id_bodywork = a.id
WHERE b.id is NULL;

SELECT a.name AS motor FROM Motor As a LEFT JOIN Car AS b ON id_bodywork = a.id
WHERE b.id is NULL;

SELECT a.name AS transmission FROM Transmission As a LEFT JOIN Car AS b ON id_bodywork = a.id
WHERE b.id is NULL;
