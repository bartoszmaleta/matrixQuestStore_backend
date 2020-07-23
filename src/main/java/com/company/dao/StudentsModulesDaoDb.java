package com.company.dao;

import java.util.List;

public class StudentsModulesDaoDb implements StudentsModulesDao {

    ConnectionFactory connectionFactory;

    public StudentsModulesDaoDb() {
        this.connectionFactory = new ConnectionFactory();
    }

    public StudentsModulesDaoDb(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List getAllElements() {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean edit(Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {

        return false;
    }
}
