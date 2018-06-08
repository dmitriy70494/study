0_url
jdbc:postgresql://localhost:5432/magnit
1_username
postgres
2_password
Lbvbnhbq123
3_check_table0
SELECT tablename FROM pg_tables WHERE tablename = 'entry'
4_create_table0
CREATE TABLE entry (field integer)
5_create_function_delete_all_items_and_insert_index_elements
CREATE OR REPLACE FUNCTION add_value_insert(integer) RETURNS void AS $$ DECLARE index integer; BEGIN DELETE FROM entry; FOR index IN 1..$1  LOOP INSERT INTO entry(field) VALUES (index); END LOOP; END; $$ LANGUAGE plpgsql
6_start_function_delete_all_items_and_insert_index_elements
SELECT add_value_insert (?)
7_check_worked_function_delete_all_items_and_insert_index_elements
SELECT COUNT(field) FROM ENTRY