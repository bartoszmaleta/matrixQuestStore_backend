package com.company.controller;


import com.company.model.Quest;
import com.company.model.user.Role;
import com.company.model.user.User;
import com.company.service.InputTaker;
import com.company.service.MentorService;
import com.company.view.AwardsView;
import com.company.view.QuestsView;
import com.company.view.TerminalView;
import com.company.view.UserView;

import java.io.FileNotFoundException;

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
            UserView.mentorMenu();
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

    // --------------------------------------
    // Student
    private void studentManagmentMenu() throws FileNotFoundException {
        boolean isRunning = true;

        while (isRunning) {
            UserView.studentManagmentMenu();
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> displayStudents();
                case 2 -> createStudentAccount();
                case 3 -> updateStudent();
                case 4 -> deleteStudentById();
                case 0 -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }

    private void displayStudents() throws FileNotFoundException {
        mentorService.displayAllStudents();
    }

    public void createStudentAccount() {
        mentorService.addUserToDb("1");
    }

    public void updateStudent() throws FileNotFoundException {
        mentorService.displayAllStudents();

        int idOfStudentToUpdate = InputTaker.takeIntInputWithMessage("Enter id of student you want to update: ");
        UserView.updateStudentModes();
        String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");

        switch (option) {
            case "1" -> updateStudentName(idOfStudentToUpdate);
            case "2" -> updateStudentSurname(idOfStudentToUpdate);
            case "3" -> updateStudentLogin(idOfStudentToUpdate);
            case "4" -> updateStudentPassword(idOfStudentToUpdate);
            case "5" -> updateStudentEmail(idOfStudentToUpdate);
            case "6" -> updateStudentAvatarPath(idOfStudentToUpdate);
            case "0" -> studentManagmentMenu();
        }
    }

    private void updateStudentEmail(int idOfStudentToUpdate) {
        String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
        mentorService.updateUserEmailById(idOfStudentToUpdate, emailToUpdate);
    }

    private void updateStudentPassword(int idOfStudentToUpdate) {
        String passwordToUpdate = InputTaker.takeStringInputWithMessage("Enter new password: ");
        mentorService.updateUserPasswordById(idOfStudentToUpdate, passwordToUpdate);
    }

    private void updateStudentLogin(int idOfStudentToUpdate) {
        String loginToUpdate = InputTaker.takeStringInputWithMessage("Enter new login: ");
        mentorService.updateUserLoginById(idOfStudentToUpdate, loginToUpdate);
    }

    private void updateStudentSurname(int idOfStudentToUpdate) {
        String surnameToUpdate = InputTaker.takeStringInputWithMessage("Enter new surname: ");
        mentorService.updateUserSurnameById(idOfStudentToUpdate, surnameToUpdate);
    }

    private void updateStudentName(int idOfStudentToUpdate) {
        String nameToUpdate = InputTaker.takeStringInputWithMessage("Enter new name: ");
        mentorService.updateUserNameById(idOfStudentToUpdate, nameToUpdate);
    }

    private void updateStudentAvatarPath(int idOfStudentToUpdate) {
        String newAvatarPath = InputTaker.takeStringInputWithMessage("Enter new avatar path: ");
        mentorService.updateUserAvatarPathById(idOfStudentToUpdate, newAvatarPath);
    }

    public void deleteStudentById() {
        int studentIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of student you want to delete: ");
//        mentorService.deleteUserFromDatabaseById(studentIdToRemove);
//        mentorService.deleteStudentDetailsById(studentIdToRemove);
        mentorService.deleteStudentById(studentIdToRemove);
    }

    // --------------------------------------
    // Quests
    private void questsManagmentMenu() throws FileNotFoundException {
        boolean isRunning = true;

        while (isRunning) {
            QuestsView.questManagmentMenu();
            String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");
            switch (option) {
                case "1" -> displayAllQuests();
                case "2" -> addQuest();
                case "3" -> updateQuest();
                case "4" -> deleteQuestById();
                case "5" -> displayQuestsByThisMentor();
                case "6" -> mentorService.getAllQuestsOfThisMentorByUserId(this.user.getId());
                case "0" -> isRunning = false;
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
        // TODO:
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

        mentorService.displayQuestsModes();

        boolean isRunning = true;
        while (isRunning) {
            String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");
            switch (option) {
                case "1" -> updateQuestTitle(idOfQuestToUpdate);
                case "2" -> updateQuestDescription(idOfQuestToUpdate);
                case "3" -> updateQuestCoinsAmount(idOfQuestToUpdate);
                case "4" -> updateQuestMentorId(idOfQuestToUpdate);
                case "5" -> questsManagmentMenu();
                case "0" -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }

    private void updateQuestMentorId(int idOfQuestToUpdate) {
        int idOfQuestMentorToUpdate = InputTaker.takeIntInputWithMessage("Enter new id of mentor: ");
        mentorService.updateQuestIdMentorById(idOfQuestToUpdate, idOfQuestMentorToUpdate);
    }

    private void updateQuestCoinsAmount(int idOfQuestToUpdate) {
        int coinsToUpdate = InputTaker.takeIntInputWithMessage("Enter new amount of coins: ");
        mentorService.updateQuestCoinsById(idOfQuestToUpdate, coinsToUpdate);
    }

    private void updateQuestDescription(int idOfQuestToUpdate) {
        String descriptionToUpdate = InputTaker.takeStringInputWithMessage("Enter new description: ");
        mentorService.updateQuestDescriptionById(idOfQuestToUpdate, descriptionToUpdate);
    }

    private void updateQuestTitle(int idOfQuestToUpdate) {
        String titleToUpdate = InputTaker.takeStringInputWithMessage("Enter new title: ");
        mentorService.updateQuestTitleById(idOfQuestToUpdate, titleToUpdate);
    }

    public void deleteQuestById() {
        int questIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of quest you want to delete: ");
        mentorService.deleteQuestById(questIdToRemove);
    }

    // --------------------------------------
    // Awards
    public void awardsManagmentMenu() throws FileNotFoundException {
        boolean isRunning = true;

        while (isRunning) {
            AwardsView.awardsManagmentMenu();

            String option = InputTaker.takeStringInputWithMessageForFirstInput("Choose: ");
            switch (option) {
//                case "1" -> awardDAO.readAllAwardsOrderById();
                case "1" -> displayAwards();
                case "2" -> addAward();
                case "3" -> updateAward();
                case "4" -> deleteAwardById();
                case "5" -> displayAwardsByThisMentor();
                case "0" -> isRunning = false;
                default -> TerminalView.printString("Wrong input.");
            }
        }
    }

    private void displayAwards() throws FileNotFoundException {
        this.mentorService.displayAllAwards();
    }

    private void displayAwardsByThisMentor() throws FileNotFoundException {
        this.mentorService.displayAllAwardsOfThisMentor(this.user);
    }

    public void addAward() {
        mentorService.addAwardToDatabase(this.user);
    }


    public void updateAward() throws FileNotFoundException {
        displayAwards();

        int idOfAwardToUpdate = InputTaker.takeIntInputWithMessage("Enter id of award you want to edit: ");
        AwardsView.updateAwardModes();

        int option = InputTaker.takeIntInputWithMessage("Choose: ");
        switch (option) {
            case 1:
                updateAwardTitle(idOfAwardToUpdate);
                break;
            case 2:
                updateAwardDescription(idOfAwardToUpdate);
                break;
            case 3:
                updateAwardPrice(idOfAwardToUpdate);
                break;
            case 4:
                updateAwardCreatorId(idOfAwardToUpdate);
                break;
            case 0:
                questsManagmentMenu();
            default:
                TerminalView.printString("Wrong input.");
        }
    }

    private void updateAwardCreatorId(int idOfAwardToUpdate) throws FileNotFoundException {
        mentorService.displayAllMentors();

        int idOfCreatorToUpdate = InputTaker.takeIntInputWithMessage("Enter new id of award creator: ");
        mentorService.updateAwardCreatorIdById(idOfAwardToUpdate, idOfCreatorToUpdate);
    }

    private void updateAwardPrice(int idOfAwardToUpdate) {
        int priceToUpdate = InputTaker.takeIntInputWithMessage("Enter new price: ");
        mentorService.updateAwardPriceById(idOfAwardToUpdate, priceToUpdate);
    }

    private void updateAwardDescription(int idOfAwardToUpdate) {
        String descriptionToUpdate = InputTaker.takeStringInputWithMessage("Enter new description: ");
        mentorService.updateAwardDescriptionById(idOfAwardToUpdate, descriptionToUpdate);
    }

    private void updateAwardTitle(int idOfAwardToUpdate) {
        String titleToUpdate = InputTaker.takeStringInputWithMessage("Enter new title: ");
        mentorService.updateAwardTitleById(idOfAwardToUpdate, titleToUpdate);
    }

    public void deleteAwardById() {
        int awardIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of award you want to delete: ");
        mentorService.deleteAwardById(awardIdToRemove);
    }

    // --------------------------------------
    // Marking
    public void markStudentAchievedQuests() {
//        TODO:
    }
}
