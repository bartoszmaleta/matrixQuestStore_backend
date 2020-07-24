package com.company.service;

import com.company.dao.ConnectionFactory;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.User;

public class LoginService {
    private final UserDao userDao;

    public LoginService() {
        this.userDao = new UserDaoDb();
    }

    public LoginService(ConnectionFactory connectionFactory) {
        this.userDao = new UserDaoDb(connectionFactory);
    }

    public User readUserWithEmailAndPassword(String email, String password) {
        return this.userDao.readUserByEmailAndPassword(email, password);
    }
}
