package org.example.dao;

import java.util.List;

public interface DAO<T> {

    T find(int id);

    List<T> findAll();

    void insert(T t);

    void delete(T t);

    void update(T t);

}
