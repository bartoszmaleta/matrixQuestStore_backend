package com.company.controllers;

import com.company.models.users.User;
import com.company.service.InputTaker;

public class AdminController {
    private User user;

    public AdminController(User user) {
        this.user = user;
        System.out.println("Admin Controller");
        System.out.println("Hello, Your email: " + this.user.getEmail());
    }

    public void init() {
        System.out.println("Admin Controller init");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("(1) Student managment\n" +
                    "(2) Quest managment\n" +
                    "(3) Awards managment\n" +
                    "(4) My profile\n" +
                    "(0) Quit");
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> addMentor();
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
