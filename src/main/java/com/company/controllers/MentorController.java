package com.company.controllers;


import com.company.models.Award;
import com.company.models.Quest;
import com.company.models.users.Role;
import com.company.models.users.Student;
import com.company.models.users.User;
import com.company.service.InputTaker;
import com.company.service.MentorService;
import com.company.view.TerminalView;
import com.company.view.View;

import java.io.FileNotFoundException;
import java.util.Date;
import java.sql.Timestamp;

public class MentorController {

    private Role role;
    private User user;

    private final MentorService mentorService;

    public MentorController(User user) {
        this.user = user;
        this.mentorService = new MentorService();
    }

    public void init() throws FileNotFoundException {
        boolean isRunning = true;
        while (isRunning) {
            View.mentorMenu();
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> studentManagmentMenu();
                case 2 -> questsManagmentMenu();
                case 3 -> awardsManagmentMenu();
                case 4 -> myProfile();
                case 0 -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }

    private void myProfile() {
        TerminalView.printString("My profile:\n"
                + this.user.getLogin() + " "
                + this.user.getPassword() + " "
                + this.user.getEmail() + " "
                + this.user.getRole());
    }


    private void studentManagmentMenu() throws FileNotFoundException {
        boolean isRunning = true;

        while (isRunning) {
            View.studentManagmentMenu();
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
//                case 1 -> userDaoDb.readUsers();
                case 1 -> View.viewAllStudents();
                case 2 -> createStudentAccount();
                case 3 -> updateStudent();
                case 4 -> deleteStudentbyId();
                case 0 -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }   public void createStudentAccount() {
        String studentName = InputTaker.takeStringInputWithMessageForFirstInput("Enter student name: ");
        String studentSurname = InputTaker.takeStringInputWithMessage("Enter student surname: ");
        String studentLogin = InputTaker.takeStringInputWithMessage("Enter student login");
        String studentPassword = InputTaker.takeStringInputWithMessage("Enter student password");
        String studentEmail = InputTaker.takeStringInputWithMessage("Enter student email");

        Student student = new Student(studentLogin, studentPassword, studentEmail, Role.STUDENT, studentName, studentSurname, 1);
        mentorService.addUserToDatabase(student);

    }


    public void updateStudent() throws FileNotFoundException {
        View.viewAllMentors();

        int idOfStudentToUpdate = InputTaker.takeIntInputWithMessage("Enter id of student you want to update: ");
        View.updateStudentModes();
        String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");

        switch (option) {
            case "1" -> {
                String nameToUpdate = InputTaker.takeStringInputWithMessage("Enter new name: ");
                mentorService.updateUserNameById(idOfStudentToUpdate, nameToUpdate);
            }
            case "2" -> {
                String surnameToUpdate = InputTaker.takeStringInputWithMessage("Enter new surname: ");
                mentorService.updateUserSurnameById(idOfStudentToUpdate, surnameToUpdate);
            }
            case "3" -> {
                String loginToUpdate = InputTaker.takeStringInputWithMessage("Enter new login: ");
                mentorService.updateUserLoginById(idOfStudentToUpdate, loginToUpdate);
            }
            case "4" -> {
                String passwordToUpdate = InputTaker.takeStringInputWithMessage("Enter new password: ");
                mentorService.updateUserPasswordById(idOfStudentToUpdate, passwordToUpdate);
            }
            case "5" -> {
                String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
                mentorService.updateUserEmailById(idOfStudentToUpdate, emailToUpdate);
            }
            case "0" -> studentManagmentMenu();
        }
    }

    public void deleteStudentbyId() {
        int studentIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of student you want to delete: ");
        mentorService.deleteUserFromDatabaseById(studentIdToRemove);
    }

    private void questsManagmentMenu() throws FileNotFoundException {
        boolean isRunning = true;

        while (isRunning) {
           View.questManagmentMenu();
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
//                case 1 -> questDAO.readQuestList();
                case 1 -> displayAllQuests();
                case 2 -> addQuest();
                case 3 -> updateQuest();
                case 4 -> deleteQuestById();
                case 5 -> displayQuestsByThisMentor();
                case 0 -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }

    private void displayQuestsByThisMentor() throws FileNotFoundException {
        this.mentorService.displayAllQuestsOfThisMentor(this.user);
    }

    private void displayAllQuests() throws FileNotFoundException {
        this.mentorService.displayAllQuests();
    }

    public void addQuest() {
        String questTitle = InputTaker.takeStringInputWithMessageForFirstInput("Enter title of quest: ");
        String questDescription = InputTaker.takeStringInputWithMessage("Enter description of quest: ");
        int questCoins = InputTaker.takeIntInputWithMessage("Enter amount of coins it costs: ");
        String questImage = InputTaker.takeStringInputWithMessageForFirstInput("Enter image name: ");
//        int questMentorId = InputTaker.takeIntInputWithMessage("Enter id of mentor: ");
        int questMentorId = this.user.getId();

        // TODO: date in quest!!
//        Date date = new Date();

        Quest questToAdd = new Quest(questTitle, questDescription, questCoins, questImage, questMentorId);
        mentorService.addQuestToDatabase(questToAdd);
    }

    public void updateQuest() throws FileNotFoundException {
        mentorService.displayAllQuestsOfThisMentor(this.user);
        int idOfQuestToUpdate = InputTaker.takeIntInputWithMessage("Enter id of quest you want to edit: ");
        View.updateQuestModes();
//        int option = InputTaker.takeIntInputWithMessage("Choose: ");
        String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");
        switch (option) {
            case "1":
                String titleToUpdate = InputTaker.takeStringInputWithMessage("Enter new title: ");
                mentorService.updateQuestTitleById(idOfQuestToUpdate, titleToUpdate);
                break;
            case "2":
                String descriptionToUpdate = InputTaker.takeStringInputWithMessage("Enter new description: ");
                mentorService.updateQuestDescriptionById(idOfQuestToUpdate, descriptionToUpdate);
                break;
            case "3":
                int coinsToUpdate = InputTaker.takeIntInputWithMessage("Enter new amount of coins: ");
                mentorService.updateQuestCoinsById(idOfQuestToUpdate, coinsToUpdate);
                break;
            case "4":
                int idOfQuestMentorToUpdate = InputTaker.takeIntInputWithMessage("Enter new id of mentor: ");
                mentorService.updateQuestIdMentorById(idOfQuestToUpdate, idOfQuestMentorToUpdate);
                break;
            case "0":
                questsManagmentMenu();
                break;
            default:
                TerminalView.printString("Wrong input.");
        }

    }

    public void deleteQuestById() {
        int questIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of quest you want to delete: ");
        mentorService.deleteQuestById(questIdToRemove);
    }

    public void awardsManagmentMenu() throws FileNotFoundException {
        boolean isRunning = true;

        while (isRunning) {
            View.awardsManagmentMenu();
            String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");
            switch (option) {
//                case "1" -> awardDAO.readAllAwardsOrderById();
                case "1" -> View.viewAllAwardsWithMentors();
                case "2" -> addAward();
                case "3" -> updateAward();
                case "4" -> deleteAwardById();
                case "0" -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }

    public void addAward() {
        String awardTitle = InputTaker.takeStringInputWithMessage("Enter title of award: ");
        String awardDescription = InputTaker.takeStringInputWithMessage("Enter description of award: ");
        int awardPrice = InputTaker.takeIntInputWithMessage("Enter price of award: ");
        String awardImage = InputTaker.takeStringInputWithMessage("Enter image name: ");
//        int awardCreatorId = InputTaker.takeIntInputWithMessage("Enter id of award creator: ");
        int awardCreatorId = this.user.getId();

        Date date = new Date();
        Award awardToAdd = new Award(awardTitle, awardDescription, awardPrice, awardImage, new Timestamp(date.getTime()), awardCreatorId);
        mentorService.addAwardToDatabase(awardToAdd);
    }


    public void updateAward() throws FileNotFoundException {
        mentorService.displayAllAwards();
        int idOfAwardToUpdate = InputTaker.takeIntInputWithMessage("Enter id of award you want to edit: ");
        View.updateAwardModes();
        int option = InputTaker.takeIntInputWithMessage("Choose: ");
        switch (option) {
            case 1:
                String titleToUpdate = InputTaker.takeStringInputWithMessage("Enter new title: ");
                mentorService.updateAwardTitleById(idOfAwardToUpdate, titleToUpdate);
                break;
            case 2:
                String descriptionToUpdate = InputTaker.takeStringInputWithMessage("Enter new description: ");
                mentorService.updateAwardDescriptionById(idOfAwardToUpdate, descriptionToUpdate);
                break;
            case 3:
                int priceToUpdate = InputTaker.takeIntInputWithMessage("Enter new price: ");
                mentorService.updateAwardPriceById(idOfAwardToUpdate, priceToUpdate);
                break;
            case 4:
                int idOfCreatorToUpdate = InputTaker.takeIntInputWithMessage("Enter new id of award creator: ");
                mentorService.updateAwardCreatorIdById(idOfAwardToUpdate, idOfCreatorToUpdate);
            case 0:
                questsManagmentMenu();
            default:
                TerminalView.printString("Wrong input.");
        }
    }

    public void deleteAwardById() {
        int awardIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of award you want to delete: ");
        mentorService.deleteAwardById(awardIdToRemove);
    }

    public MentorService getMentorService() {
        return mentorService;
    }

    public void markStudentAchievedQuests() {

    }
}
