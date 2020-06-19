package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.Role;
import com.company.models.users.Student;
import com.company.models.users.User;
import com.company.view.View;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class EmployeeService  {
    public UserDao userDao;

    public EmployeeService() {
        this.userDao = new UserDaoDb();
    }

    public void addMentorToDatabase() {

        String mentorName = InputTaker.takeStringInputWithMessageForFirstInput("Enter mentor name: ");
        String mentorSurname = InputTaker.takeStringInputWithMessage("Enter mentor surname: ");
        String mentorLogin = InputTaker.takeStringInputWithMessage("Enter mentor login");
        String mentorPassword = InputTaker.takeStringInputWithMessage("Enter mentor password");
        String mentorEmail = InputTaker.takeStringInputWithMessage("Enter mentor email");

        Student mentor = new Student(mentorLogin, mentorPassword, mentorEmail, Role.MENTOR, mentorName, mentorSurname, 1);

        userDao.insert(mentor);
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

    public void displayAllStudents() throws FileNotFoundException {
        List<User> students = this.userDao.getStudents();
        View.allStudents(students);
    }
}
