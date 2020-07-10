package com.company.dao;

import com.company.model.user.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User readUserByEmailAndPassword(String userEmail, String userPassword);
    List<User> getStudents();
    List<User> getMentors();
    void updateUserNameById(int id, String name);
    void updateUserSurnameById(int id, String surname);
    void updateUserLoginById(int id, String login);
    void updateUserPasswordById(int id, String password);
    void editUserEmailById(int id, String email);
    void updateStudentAvatarPathById(int id, String avatarPath);

    int readUserIdByEmail(String email);
    boolean deleteStudentDetails(int id);
    boolean deleteStudent(int id);
    User readUserById(int userId);
}
