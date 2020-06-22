package com.company.controllers;

import com.company.models.users.Role;
import com.company.models.users.User;
import com.company.service.InputTaker;
import com.company.service.StudentService;
import com.company.view.TerminalView;

import java.io.FileNotFoundException;

public class StudentController {
    private User user;
    private Role role;
    private StudentService studentService;


    public StudentController(User user) {
        this.user = user;
        this.studentService = new StudentService();
        System.out.println("Student Controller");
        System.out.println("Hello, Your email: " + this.user.getEmail());
    }

    public void init() throws FileNotFoundException {
        System.out.println("Student Controller init");

        boolean isRunning = true;
        while (isRunning) {
            studentService.displayMenu();

            String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose Your option:");
            switch (option) {
                case "1" -> studentService.displayMyAwards(this.user);
                case "2" -> studentService.buyAward(this.user);
                case "3" -> studentService.displayMyTransactions(this.user);
                case "4" -> studentService.displayMyProfile(this.user);
                case "5" -> studentService.displayQuests();
                case "0" -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }
}
