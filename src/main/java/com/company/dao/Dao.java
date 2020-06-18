package com.company.dao;

import java.util.List;

public interface Dao<T> {

    public List<T> getAllElements();

    public T getById(int id);

    public boolean insert(T t);

    public boolean edit(T t);

    public boolean delete(int id);

}
