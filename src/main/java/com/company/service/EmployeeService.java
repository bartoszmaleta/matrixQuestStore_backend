package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.Role;
import com.company.model.user.Student;
import com.company.model.user.User;
import com.company.view.QuestsView;
import com.company.view.TerminalView;
import com.company.view.UserView;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class EmployeeService {
    public UserDao userDao;

    public EmployeeService() {
        this.userDao = new UserDaoDb();
    }

    public void addUserToDb(String roleString) {
        Role userRole = decideRole(roleString);

        TerminalView.printString("Enter credentials for new " + userRole + ":\n");

        String name = InputTaker.takeStringInputWithMessageForFirstInput("Enter name: ");
        String surname = InputTaker.takeStringInputWithMessage("Enter surname: ");
        String login = InputTaker.takeStringInputWithMessage("Enter login");
        String password = InputTaker.takeStringInputWithMessage("Enter password");
        String email = InputTaker.takeStringInputWithMessage("Enter email");
        String avatarSource = InputTaker.takeStringInputWithMessage("Enter avatar path: ");
        // TODO user_detail??
//        User newUser = new Student(login, password, email, userRole, name, surname, 1);

        User newUser = new Student(name, surname, login, password, email, 1, avatarSource);


        userDao.insert(newUser);
    }

    public Role decideRole(String role) {
        if (role.equals("1")) {
            return Role.STUDENT;
        } else if (role.equals("2")) {
            return Role.MENTOR;
        } else if (role.equals("3")) {
            return Role.ADMIN;
        }
        return null;
    }

    public User readUserFromDaoById(int id) {
        return this.userDao.readUserById(id);
    }

    public void deleteStudentById(int id) {
        this.userDao.deleteStudent(id);
        this.userDao.deleteStudentDetails(id);

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

    public void updateUserPasswordById(int id, String password) {
        userDao.updateUserPasswordById(id, password);
    }

    public void updateUserSurnameById(int id, String surname) {
        userDao.updateUserSurnameById(id, surname);
    }

    public void updateUserAvatarPathById(int id, String avatarPath) {
        userDao.updateStudentAvatarPathById(id, avatarPath);
    }

    public void displayAllStudents() throws FileNotFoundException {
        List<User> students = this.userDao.getStudents();
        UserView.allStudents(students);
    }

    public void displayQuestsModes() {
        QuestsView.updateQuestModes();
    }

    public void displayAllMentors() throws FileNotFoundException {
        List<User> mentors = this.userDao.getMentors();
        UserView.allMentors(mentors);
    }

    public List<User> getAllMentors() throws FileNotFoundException {
        List<User> mentors = this.userDao.getMentors();
        return mentors;
    }


}
