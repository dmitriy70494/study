В системе заданы таблицы 

product(id, name, type_id, expired_date, price)

type(id, name)

Задание.

1. Написать запрос получение всех продуктов с типом "СЫР"

SELECT * FROM product
WHERE type_id IN(SELECT id FROM type
WHERE name = 'СЫР');

2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

SELECT * FROM product AS p
WHERE p.name like '%мороженное%';

3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

SELECT * FROM product
WHERE expired_date BETWEEN '2018-07-01 00:00:00' AND '2018-07-31 23:59:59';

4. Написать запрос, который вывод самый дорогой продукт.

SELECT * FROM product
WHERE price IN(SELECT MAX(price) FROM product);

5. Написать запрос, который выводит количество всех продуктов определенного типа.

SELECT COUNT(type_id) AS type_count FROM product
WHERE type_id IN(SELECT id FROM type
WHERE name = 'СЫР');

6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

SELECT * FROM product
WHERE type_id IN(SELECT id FROM type
WHERE name = 'СЫР' OR name = 'МОЛОКО');

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.

SELECT name FROM type
WHERE id = (SELECT type_id FROM product 
GROUP BY type_id
HAVING COUNT(name) < 10);  

8. Вывести все продукты и их тип.

SELECT id, a.name, b.name AS type, expired_date, price FROM product AS a
INNER JOIN type AS b ON a.type_id = b.id
ORDER BY a.name;
