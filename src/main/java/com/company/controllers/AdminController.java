package com.company.controllers;

import com.company.models.users.User;
import com.company.service.InputTaker;
import com.company.view.View;

import java.io.FileNotFoundException;

public class AdminController {
    private User user;

    public AdminController(User user) {
        this.user = user;
        System.out.println("Admin Controller");
        System.out.println("Hello, Your email: " + this.user.getEmail());
    }

    public void init() throws FileNotFoundException {
        System.out.println("Admin Controller init");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n(1) Display all users\n\n" +
                    "(2) Display all mentors\n" +
                    "(3) Add mentor\n" +
                    "(4) Delete mentor\n" +
                    "(5) Edit mentor\n\n" +

                    "(6) Display all modules\n" +
                    "(7) Add module\n" +
                    "(8) Delete module\n" +
                    "(9) Edit module\n\n" +

                    "(10) My profile\n" +
                    "(0) Quit");
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> View.viewAllUsers();
                case 2 -> addMentor();
                case 3 -> addMentor();
                case 4 -> addMentor();
                case 5 -> addMentor();
                case 6 -> addMentor();
                case 7 -> addMentor();
                case 8 -> addMentor();
                case 9 -> addMentor();
                case 10 -> addMentor();
//                case 2 -> editMentor();
//                case 3 -> addModule();
//                case 4 -> editModule();
//                case 4 -> editStudent();
                case 0 -> isRunning = false;
                default -> System.out.println("Wrong input.");
            }
        }
    }

    private void addMentor() {

    }
}
