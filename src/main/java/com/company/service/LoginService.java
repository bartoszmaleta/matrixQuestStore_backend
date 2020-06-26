package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.User;

public class LoginService {
    private UserDao userDao;

    public LoginService() {
        this.userDao = new UserDaoDb();
    }


    public User readUserFromDaoByEmailOrPassword(String email, String password) {
        return this.userDao.readUserByEmailOrLoginAndPassword(email, password);
    }
}
