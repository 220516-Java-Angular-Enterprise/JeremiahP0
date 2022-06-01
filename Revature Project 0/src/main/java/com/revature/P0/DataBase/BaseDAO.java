package com.revature.P0.DataBase;

import java.util.List;
public interface BaseDAO<T> {
    void save(T obj);

    void update(T obj);

    void delete(String id);

    T getById(String id);

    List<T> getAll();
}
