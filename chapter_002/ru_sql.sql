0_url
jdbc:postgresql://localhost:5432/postgres
1_url
jdbc:postgresql://localhost:5432/ru_sql
2_username
postgres
3_password
Lbvbnhbq123
4_check_database
SELECT 1 FROM pg_database WHERE datname = 'ru_sql'
5_greate_database
CREATE DATABASE ru_sql
6_number_last_command_for_create_table_in_database
10
7_check_table_employer
SELECT tablename FROM pg_tables WHERE tablename = 'employer'
8_create_table_employer
CREATE TABLE employer (id serial primary key, name character varying (100), link text)
9_check_table_employer
SELECT tablename FROM pg_tables WHERE tablename = 'job'
10_create_table_employer
CREATE TABLE job (id serial primary key, title text, link text, description text, id_employer integer references employer, dates timestamp)
11_insert_in_employer
INSERT INTO employer (name, link) VALUES (?, ?)
12_insert_in_job
INSERT INTO job (title, link, description, id_employer, dates) VALUES (?, ?, ?, ?, ?)
13_count_start_hours
24
14_select_last_date
SELECT MAX(dates) FROM job
15_select_job_and_employer
SELECT id FROM job WHERE title = ? AND id_employer = ?
16_select_employer
SELECT id FROM employer WHERE name = ?
17_select_for_logging
SELECT title, a.link, description, name, b.link, dates FROM job AS a INNER JOIN employer AS b ON a.id_employer = b.id WHERE dates > ? ORDER BY dates