package com.company.controller;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.Role;
import com.company.model.user.Student;
import com.company.model.user.User;
import com.company.service.InputTaker;
import com.company.service.LoginService;
import com.company.view.TerminalView;
import com.company.view.menu.LoginMenu;

import java.io.FileNotFoundException;

public class LoggingController {
    private UserDao userDao;
    private LoginService loginService;

    public LoggingController() {
        this.userDao = new UserDaoDb();
        this.loginService = new LoginService();
    }

    public void init() throws FileNotFoundException {
        boolean isRunning = true;
        TerminalView.displayWelcomeScreen();

        while (isRunning) {
            TerminalView.clearScreen();
            LoginMenu.display();

            String option = InputTaker.takeStringInputWithMessage("Choose: ");
            switch (option) {
                case "1":
                    loggingUser();
                    break;
                case "2":
                    registerAsNewStudent();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    public void registerAsNewStudent() {
        String studentName = InputTaker.takeStringInputWithMessage("Enter student name: ");
        String studentSurname = InputTaker.takeStringInputWithMessage("Enter student surname: ");
        String studentLogin = InputTaker.takeStringInputWithMessage("Enter student login");
        String studentPassword = InputTaker.takeStringInputWithMessage("Enter student password");
        String studentEmail = InputTaker.takeStringInputWithMessage("Enter student email");
        String avatarSource = InputTaker.takeStringInputWithMessage("Enter student avatar path");

        // TODO: validation if exist, if taken
//        Student student = new Student(studentLogin, studentPassword, studentEmail, Role.STUDENT, studentName, studentSurname, avatarSource);
        User student = new Student(studentName, studentSurname, studentLogin, studentPassword, studentEmail, 1, avatarSource);
        this.userDao.insert(student);
        TerminalView.printString("You have successfully registered as new student!");
    }


//    public void createNewUser() {
//        Scanner scanner = new Scanner(System.in);
//        TerminalView.printString("Enter Your username: ");
//
//        String userName = scanner.nextLine();
//        User newUser = new UsersDAO().readUserByName(userName);
//
//        while (newUser.getName() != null) {
//            TerminalView.printString("This username is taken. Enter different username!");
//            userName = scanner.nextLine();
//            newUser = new UsersDAO().readUserByName(userName);
//        }
//
//        newUser.setName(userName);
//        newUser.setIsAdmin(0);
//
//        String password = TerminalManager.askForString("Enter Your password: ");
//        newUser.setPassword(password);
//
//        String email = TerminalManager.askForString("Enter Your email: ");
//        newUser.setEmail(email);
//
//        new UsersDAO().write(newUser);
//
//        String successMessage = "Good job! You have just created new User in DB! ";
//        System.out.println(Chalk.on(successMessage).cyan().underline());
//    }

    public void loggingUser() throws FileNotFoundException {

        String email = InputTaker.takeStringInputWithMessage("Enter email: ");
        String password = InputTaker.takeStringInputWithMessage("Enter pasword: ");
//        this.userDaoDb.readUsers();

        User userToLog = this.userDao.readUserByEmailAndPassword(email, password);

        if (userToLog == null) {
            TerminalView.printString("Wrong email or password.");
        } else {
//            userToLog


            if (userToLog.getRole() == Role.ADMIN) {
                AdminController adminController = new AdminController(userToLog);
                adminController.init();
            } else if (userToLog.getRole() == Role.MENTOR) {
                MentorController mentorController = new MentorController(userToLog);
                mentorController.init();
            } else if (userToLog.getRole() == Role.STUDENT) {
                StudentController studentController = new StudentController(userToLog);
                studentController.init();
            }
        }
    }

    public User login(String email, String password) {
        return loginService.readUserWithEmailAndPassword(email, password);
    }
}
