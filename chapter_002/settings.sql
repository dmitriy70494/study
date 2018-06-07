url_root
jdbc:postgresql://localhost:5432/postgres
url_database
jdbc:postgresql://localhost:5432/items_system
username
postgres
password
Lbvbnhbq123
check_database
SELECT 1 FROM pg_database WHERE datname = 'items_system'
create_database
CREATE DATABASE items_system
check_table0
SELECT tablename FROM pg_tables WHERE tablename = 'items'
create_table0
CREATE TABLE items (id serial primary key, name character varying(100), description text, create_date timestamp)
check_table1
SELECT tablename FROM pg_tables WHERE tablename = 'comments'
create_table1
CREATE TABLE comments (id serial primary key, description text, id_items integer references items(id))
insert_user
INSERT INTO items (name, description, create_date) VALUES (?, ?, ?)
update_user
UPDATE items SET name = ?, description = ? where id = ?
delete_user
DELETE FROM items WHERE id = ?
select_users
SELECT id, name, description FROM items
select_name
SELECT id, name, description FROM items WHERE name = ?
select_id
SELECT id, name, description FROM items WHERE id = ?
