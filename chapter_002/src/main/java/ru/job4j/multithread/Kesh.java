package ru.job4j.multithread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * класс Kesh. Хранит и обрабатывает модели.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.06.2018
 */
public class Kesh {

    /**
     * Хранит модели
     */
    private ConcurrentHashMap<Integer, Base> storage = new ConcurrentHashMap<Integer, Base>();

    /**
     * Добавляет модель в хранилище
     * @param model
     */
    public void add(Base model) {
        storage.put(model.getId(), model);
    }

    /**
     * Вносит изменения в хранилище. Если модель изменена одним пользователем и другой пользователь вносит изменения на
     * основании старых данных, то выскочит исключение, если нет, то изменения внесутся в базу данных
     *
     * @param model
     * @throws OptimisticException
     */
    public void update(Base model) throws OptimisticException {
        storage.computeIfPresent(model.getId(), (k, v) -> {
            if (v.getVersion() + 1 != model.getVersion()) {
                throw new OptimisticException("OptimisticException");
            }
            return model; });
    }

    /**
     * Удаляет модель из списка
     * @param model
     */
    public void delete(Base model) {
        storage.remove(model.getId());
    }

    /**
     * Получает модель по id
     * @param id
     * @return
     */
    public Base get(Integer id) {
        return storage.get(id);
    }
}
