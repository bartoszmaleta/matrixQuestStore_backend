package com.company.controllers;

import com.company.dao.UserDaoDb;
import com.company.models.users.Role;
import com.company.models.users.Student;
import com.company.models.users.User;
import com.company.service.InputTaker;

public class MentorController {

    private Role role;
    private User user;
    UserDaoDb userDaoDb = new UserDaoDb();

    public void init() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("(1) Student managment\n" +
                    "(2) Quest managment\n" +
                    "(3) Awards managment\n" +
                    "(0) Quit");
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> studentManagmentMenu();
                case 2 -> questsManagmentMenu();
                case 3 -> awardsManagmentMenu();
                case 0 -> isRunning = false;
                default -> System.out.println("Wrong input.");
            };
        }
    }

    public void createStudentAccount() {
        String studentName = InputTaker.takeStringInputWithMessageForFirstInput("Enter student name: ");
        String studentSurname = InputTaker.takeStringInputWithMessage("Enter student surname: ");
        String studentLogin = InputTaker.takeStringInputWithMessage("Enter student login");
        String studentPassword = InputTaker.takeStringInputWithMessage("Enter student password");
        String studentEmail = InputTaker.takeStringInputWithMessage("Enter student email");

        Student student = new Student(studentLogin, studentPassword, studentEmail, Role.STUDENT, studentName, studentSurname, 1);
        userDaoDb.addUserToDatabase(student);

    }

    public void deleteStudentbyId() {
        int studentIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of student you want to delete: ");
        userDaoDb.deleteUserById(studentIdToRemove);
    }

    public void addQuest() {

    }

    public void updateQuest() {


    }

    public void addAward() {

    }

    public void markStudentAchievedQuests() {

    }

    private void awardsManagmentMenu() {
    }

    private void questsManagmentMenu() {
    }

    private void studentManagmentMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("(1) View students table\n" +
                    "(2) Create student\n" +
                    "(3) Update student data\n" +
                    "(4) Delete student\n" +
                    "(5) View students wallets\n" +
                    "(0) Quit");
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> userDaoDb.readUsers();
                case 2 -> createStudentAccount();
                case 3 -> updateStudent();
                case 4 -> deleteStudentbyId();
                case 0 -> isRunning = false;
                default -> System.out.println("Wrong input.");
            }


        }
    }
    public void updateStudent() {
        userDaoDb.readUsers();
        int idToDelete = InputTaker.takeIntInputWithMessage("Enter id of student you want to update: ");
        System.out.println("" +
                "(1) Name\n" +
                "(2) Surname\n" +
                "(3) Login\n" +
                "(4) Password\n" +
                "(5) Email\n" +
                "(6) Back to Student Managment Menu");

        int option = InputTaker.takeIntInputWithMessage("What do you want to edit?");
        switch (option) {
            case 1 -> {
                String nameToUpdate = InputTaker.takeStringInputWithMessage("Enter new name: ");
                userDaoDb.editUserNameById(idToDelete, nameToUpdate);
            }
            case 2 -> {
                String surnameToUpdate = InputTaker.takeStringInputWithMessage("Enter new surname: ");
                userDaoDb.editUserSurnameById(idToDelete, surnameToUpdate);
            }
            case 3 -> {
                String loginToUpdate = InputTaker.takeStringInputWithMessage("Enter new login: ");
                userDaoDb.editUserLoginById(idToDelete, loginToUpdate);
            }
            case 4 -> {
                String passwordToUpdate = InputTaker.takeStringInputWithMessage("Enter new password: ");
                userDaoDb.editUserPasswordById(idToDelete, passwordToUpdate);
            }
            case 5 -> {
                String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
                userDaoDb.editUserEmailById(idToDelete, emailToUpdate);
            }
            case 0 -> studentManagmentMenu();
        }

    }
}
