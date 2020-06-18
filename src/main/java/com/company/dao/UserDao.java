package com.company.dao;

import com.company.models.users.User;

import java.util.List;

public interface UserDao extends Dao {
    User readUserByEmailOrLoginAndPassword(String userEmail, String userPassword);
    List<User> getStudents();
    List<User> getMentors();
    void updateUserNameById(int id, String name);
    void updateUserSurnameById(int id, String surname);
    void updateUserLoginById(int id, String login);
    void updateUserPasswordById(int id, String password);
    void editUserEmailById(int id, String email);

}
