package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.User;

public class AdminService {
    private UserDao userDao;

    public AdminService() {
        this.userDao = new UserDaoDb();
    }

    public void addAdminToDatabase(User admin) {
        userDao.addUserToDatabase(admin);
    }

    public void deleteAdminFromDatabaseById(int id) {
        userDao.deleteUserById(id);
    }

    public void updateAdminLoginById(int id, String login) {
        userDao.updateUserLoginById(id, login);
    }

    public void updateAdminNameById(int id, String name) {
        userDao.updateUserNameById(id, name);
     }

    public void updateAdminEmailById(int id, String email) {
        userDao.editUserEmailById(id, email);
    }

    public void updateAdminPasswordById (int id, String password) {
        userDao.updateUserPasswordById(id, password);
    }

    public void updateAdminSurnameById (int id, String surname) {
        userDao.updateUserSurnameById(id, surname);
    }



}
