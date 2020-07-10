package com.company.service;

import com.company.dao.*;
import com.company.model.Award;
import com.company.model.StudentAward;
import com.company.model.Transaction;
import com.company.model.user.Role;
import com.company.model.user.Student;
import com.company.model.user.User;
import com.company.view.QuestsView;
import com.company.view.TerminalView;
import com.company.view.UserView;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public abstract class EmployeeService {
    private final UserDao userDao;
    private final StudentDetailsDao studentDetailsDao;
    private final StudentsAwardsDao studentsAwardsDao;
    private final TransactionDao transactionDao;

    public EmployeeService() {
        this.userDao = new UserDaoDb();
        this.studentDetailsDao = new StudentDetailsDaoDb();
        this.studentsAwardsDao = new StudentsAwardsDaoDb();
        this.transactionDao = new TransactionDaoDb();

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


        this.userDao.insert(newUser);
    }

    public void addUser(User user) {
        System.out.println("addUser befor");
        this.userDao.insert(user);
        System.out.println("addUser after");

        createStudentDetails(user); // TODO: where? here?
    }

    private void createStudentDetails(User user) {
        System.out.println("createSD");
        System.out.println("user.getId = " + user.getId());
        System.out.println("user.getEmail = " + user.getEmail());
        int studentIdFromDao = userDao.readUserIdByEmail(user.getEmail());
        this.studentDetailsDao.insert(studentIdFromDao);
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
        return (User) this.userDao.getById(id);
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
        System.out.println("qweqweqweqweqw + " + this.userDao.getMentors());
        return this.userDao.getMentors();
    }

    public List<User> getAllStudents() throws FileNotFoundException {
        return this.userDao.getStudents();
    }


    public void addAwardByAwardIdToStudentByStudentId(int awardId, int studentId, int priceOfAward) {
        System.out.println("EmployeeService addAward");

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        StudentAward studentAward = new StudentAward(studentId, awardId, priceOfAward, timestamp);
        this.studentsAwardsDao.insert(studentAward);

        Transaction transaction = new Transaction(studentId, awardId, priceOfAward, timestamp);
        this.transactionDao.insert(transaction);

        this.studentDetailsDao.subtractAwardPriceFromUserById(studentId, priceOfAward);
    }
}
