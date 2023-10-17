package ru.kpfu.itis.bagaviev.dao;

import java.util.List;

public interface Dao<T> {
    T get(Integer id);
    List<T> getAll();
    void save(T object);
}
