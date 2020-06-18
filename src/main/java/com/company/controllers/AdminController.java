package com.company.controllers;

import com.company.dao.UserDaoDb;
import com.company.models.users.Role;
import com.company.models.users.Student;
import com.company.models.users.User;
import com.company.service.AdminService;
import com.company.service.InputTaker;
import com.company.view.TerminalView;
import com.company.view.View;

import java.io.FileNotFoundException;

public class AdminController {
    private User user;
    private final UserDaoDb userDaoDb = new UserDaoDb();
    AdminService adminService;

    public AdminController(User user) {
        this.user = user;
        this.adminService = new AdminService();
    }

    public void init() throws FileNotFoundException {
//        System.out.println("Admin Controller init");

        boolean isRunning = true;
        while (isRunning) {
        View.adminMenu();
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
//                case 1 -> View.viewAllUsers();
                case 1 -> displayUsers();

                case 2 -> displayMentors();
                case 3 -> addMentor();
                case 4 -> removeMentor();
                case 5 -> editMentor();

//                case 6 -> View.viewAllModules();
//                case 7 -> addModule();
//                case 8 -> removeModule();
//                case 9 -> editModule();

//                case 10 -> displayStudents();
//                case 11 -> showProfile();
                case 0 -> isRunning = false;
                default -> TerminalView.printString("Wrong input");
            }
        }
    }

    private void editMentor() throws FileNotFoundException {
        View.viewAllMentors();

        int idOfMentorToUpdate = InputTaker.takeIntInputWithMessage("Enter id of mentor you want to update: ");
        View.updateMentorModes();

        String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");
        switch (option) {
            case "1" -> {
                String nameToUpdate = InputTaker.takeStringInputWithMessage("Enter new name: ");
                this.userDaoDb.updateUserNameById(idOfMentorToUpdate, nameToUpdate);
            }
            case "2" -> {
                String surnameToUpdate = InputTaker.takeStringInputWithMessage("Enter new surname: ");
                this.userDaoDb.updateUserSurnameById(idOfMentorToUpdate, surnameToUpdate);
            }
            case "3" -> {
                String loginToUpdate = InputTaker.takeStringInputWithMessage("Enter new login: ");
                this.userDaoDb.updateUserLoginById(idOfMentorToUpdate, loginToUpdate);
            }
            case "4" -> {
                String passwordToUpdate = InputTaker.takeStringInputWithMessage("Enter new password: ");
                this.userDaoDb.updateUserPasswordById(idOfMentorToUpdate, passwordToUpdate);
            }
            case "5" -> {
                String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
                this.userDaoDb.editUserEmailById(idOfMentorToUpdate, emailToUpdate);
            }
            case "0" -> System.out.println("Default 0");
        }
    }

    private void removeMentor() throws FileNotFoundException {
        View.viewAllMentors();
        int mentorToRemoveById = InputTaker.takeIntInputWithMessage("Enter id of mentor you want to delete: ");
        this.adminService.deleteMentor(mentorToRemoveById);
    }

    private void addMentor() {
        // TODO: MentorService and other Services!!!
//        this.mentorService = new MentorService();
//        mentorService.addMentor(this.user);  // user to service!
//        mentorService.addMentor(this.user);


        String mentorName = InputTaker.takeStringInputWithMessageForFirstInput("Enter mentor name: ");
        String mentorSurname = InputTaker.takeStringInputWithMessage("Enter mentor surname: ");
        String mentorLogin = InputTaker.takeStringInputWithMessage("Enter mentor login");
        String mentorPassword = InputTaker.takeStringInputWithMessage("Enter mentor password");
        String mentorEmail = InputTaker.takeStringInputWithMessage("Enter mentor email");

        Student mentor = new Student(mentorLogin, mentorPassword, mentorEmail, Role.MENTOR, mentorName, mentorSurname, 1);
//        this.userDaoDb.addUserToDatabase(mentor);
        this.userDaoDb.insert(mentor);
    }

    private void displayUsers() throws FileNotFoundException {
        adminService.displayAllUsers();
    }
    private void displayStudents() throws FileNotFoundException {
        adminService.displayAllMentors();
    }
    private void displayMentors() throws FileNotFoundException {
        adminService.displayAllStudents();
    }
}
