package com.company.controllers;

import com.company.models.users.User;
import com.company.service.AdminService;
import com.company.service.InputTaker;
import com.company.service.TransactionsService;
import com.company.view.TerminalView;
import com.company.view.View;

import java.io.FileNotFoundException;

public class AdminController {
    private User user;
    AdminService adminService;
    TransactionsService transactionsService;

    public AdminController(User user) {
        this.user = user;
        this.adminService = new AdminService();

    }

    public void init() throws FileNotFoundException {
        boolean isRunning = true;
        while (isRunning) {
            View.adminMenu();
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> displayUsers();

                case 2 -> displayMentors();
                case 3 -> addMentor();
                case 4 -> removeMentor();
                case 5 -> editMentor();

//                case 6 -> View.viewAllModules();
//                case 7 -> addModule();
//                case 8 -> removeModule();
//                case 9 -> editModule();

                case 10 -> displayStudents();
//                case 11 -> showProfile();
                case 12 -> displayTransactionsStatistics();
                case 0 -> isRunning = false;
                default -> TerminalView.printString("Wrong input");
            }
        }
    }

    private void displayTransactionsStatistics() {
        transactionsService.displayTransactionsCountsAndTotalSumByUser();
    }

    private void editMentor() throws FileNotFoundException {
        adminService.displayAllMentors();

        int idOfMentorToUpdate = InputTaker.takeIntInputWithMessage("Enter id of mentor you want to update: ");
        adminService.displayMentorsOptions();

        String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");
        switch (option) {
            case "1" -> adminService.updateMentorsUsername(idOfMentorToUpdate);
            case "2" -> adminService.updateMentorsSurname(idOfMentorToUpdate);
            case "3" -> adminService.updateMentorsLogin(idOfMentorToUpdate);
            case "4" -> adminService.updateMentorsPassword(idOfMentorToUpdate);
            case "5" -> {
                adminService.updateMentorsEmail(idOfMentorToUpdate);
//                String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
//                this.userDaoDb.editUserEmailById(idOfMentorToUpdate, emailToUpdate);
            }
            case "0" -> System.out.println("Default 0");
        }
    }

    private void removeMentor() throws FileNotFoundException {
        adminService.displayAllMentors();
        // TODO: WHERE inputs?? Here or in adminService???
        int mentorToRemoveById = InputTaker.takeIntInputWithMessage("Enter id of mentor you want to delete: ");
        this.adminService.deleteMentor(mentorToRemoveById);
    }

    private void addMentor() {
        adminService.addUserToDb("2");
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
