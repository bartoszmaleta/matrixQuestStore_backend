package com.company.controllers;

import com.company.models.users.User;

import java.io.FileNotFoundException;
import java.util.Scanner;
public class LoggingController {
    Scanner scanner = new Scanner(System.in);

    public void init() throws FileNotFoundException {
        boolean isRunning = true;
        TerminalView.displayWelcomeScreen();

        while (isRunning) {
            TerminalView.clearScreen();
            LoginMenu.display();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loggingUser();
                    break;
                case 2:
                    createNewUser();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    public void createNewUser() {
        Scanner scanner = new Scanner(System.in);
        TerminalView.printString("Enter Your username: ");

        String userName = scanner.nextLine();
        User newUser = new UsersDAO().readUserByName(userName);

        while (newUser.getName() != null) {
            TerminalView.printString("This username is taken. Enter different username!");
            userName = scanner.nextLine();
            newUser = new UsersDAO().readUserByName(userName);
        }

        newUser.setName(userName);
        newUser.setIsAdmin(0);

        String password = TerminalManager.askForString("Enter Your password: ");
        newUser.setPassword(password);

        String email = TerminalManager.askForString("Enter Your email: ");
        newUser.setEmail(email);

        new UsersDAO().write(newUser);

        String successMessage = "Good job! You have just created new User in DB! ";
        System.out.println(Chalk.on(successMessage).cyan().underline());

    }

    public void loggingUser() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        TerminalView.printString("User name: ");
        String name = scanner.nextLine();

        TerminalView.printString("User password: ");
        String password = scanner.nextLine();

        if (new UsersDAO().readUserByNameAndPassoword(name,password).getName()==null){
            TerminalView.printString("Wrong username or password.");
        } else {
            User user = new UsersDAO().readUserByNameAndPassoword(name,password);
            if (user.getIsAdmin() == 1) {
                // TODO:
                AdminController adminController = new AdminController(user);
                adminController.init();
            } else {
                CustomerController customerController = new CustomerController(user);
                customerController.init();
            }
        }
    }
}