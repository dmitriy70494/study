url = jdbc:postgresql://localhost:5432/magnit
username = postgres
password = Lbvbnhbq123
3_check_table0 = SELECT tablename FROM pg_tables WHERE tablename = 'entry'
4_create_table0 = CREATE TABLE entry (field integer)
5_create_function = CREATE OR REPLACE FUNCTION add_value_insert(integer) RETURNS void AS $$ DECLARE index integer; BEGIN DELETE FROM entry; FOR index IN 1..$1  LOOP INSERT INTO entry(field) VALUES (index); END LOOP; END; $$ LANGUAGE plpgsql
6_start_function = SELECT add_value_insert (?)
7_check_worked_function = SELECT COUNT(field) FROM ENTRY