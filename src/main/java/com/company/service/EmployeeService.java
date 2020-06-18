package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.User;

public abstract class EmployeeService  {
    public UserDao userDao;

    public EmployeeService() {
        this.userDao = new UserDaoDb();
    }

    public void addUserToDatabase(User user) {
//        userDao.addUserToDatabase(user);
        userDao.insert(user);
    }

    public void deleteUserFromDatabaseById(int id) {
//        userDao.deleteUserById(id);
        userDao.delete(id);
    }

    public void updateUserLoginById(int id, String login) {
        userDao.updateUserLoginById(id, login);
    }

    public void updateUserNameById(int id, String name) {
        userDao.updateUserNameById(id, name);
    }

    public void updateUserEmailById(int id, String email) {
        userDao.editUserEmailById(id, email);
    }

    public void updateUserPasswordById (int id, String password) {
        userDao.updateUserPasswordById(id, password);
    }

    public void updateUserSurnameById (int id, String surname) {
        userDao.updateUserSurnameById(id, surname);
    }


}
