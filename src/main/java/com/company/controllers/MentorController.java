package com.company.controllers;

import com.company.dao.AwardDAO;
import com.company.dao.QuestDAO;
import com.company.dao.UserDaoDb;
import com.company.models.Award;
import com.company.models.Quest;
import com.company.models.users.Role;
import com.company.models.users.Student;
import com.company.models.users.User;
import com.company.service.InputTaker;

import java.util.Date;
import java.sql.Timestamp;

public class MentorController {

    private Role role;
    private User user;
    UserDaoDb userDaoDb = new UserDaoDb();
    QuestDAO questDAO = new QuestDAO();
    AwardDAO awardDAO = new AwardDAO();

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
            }
            ;
        }
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

    public void createStudentAccount() {
        String studentName = InputTaker.takeStringInputWithMessage("Enter student name: ");
        String studentSurname = InputTaker.takeStringInputWithMessage("Enter student surname: ");
        String studentLogin = InputTaker.takeStringInputWithMessage("Enter student login: ");
        String studentPassword = InputTaker.takeStringInputWithMessage("Enter student password: ");
        String studentEmail = InputTaker.takeStringInputWithMessage("Enter student email: ");

        Student student = new Student(studentLogin, studentPassword, studentEmail, Role.STUDENT, studentName, studentSurname, 1);
        userDaoDb.addUserToDatabase(student);

    }

    public void updateStudent() {
        userDaoDb.readUsers();
        int idOfStudentToUpdate = InputTaker.takeIntInputWithMessage("Enter id of student you want to update: ");
        System.out.println("What do you want to edit? \n");
        System.out.println("" +
                "(1) Name\n" +
                "(2) Surname\n" +
                "(3) Login\n" +
                "(4) Password\n" +
                "(5) Email\n" +
                "(0) Back to Student Managment Menu");

        int option = InputTaker.takeIntInputWithMessage("Choose: ");
        switch (option) {
            case 1 -> {
                String nameToUpdate = InputTaker.takeStringInputWithMessage("Enter new name: ");
                userDaoDb.updateUserNameById(idOfStudentToUpdate, nameToUpdate);
            }
            case 2 -> {
                String surnameToUpdate = InputTaker.takeStringInputWithMessage("Enter new surname: ");
                userDaoDb.updateUserSurnameById(idOfStudentToUpdate, surnameToUpdate);
            }
            case 3 -> {
                String loginToUpdate = InputTaker.takeStringInputWithMessage("Enter new login: ");
                userDaoDb.updateUserLoginById(idOfStudentToUpdate, loginToUpdate);
            }
            case 4 -> {
                String passwordToUpdate = InputTaker.takeStringInputWithMessage("Enter new password: ");
                userDaoDb.updateUserPasswordById(idOfStudentToUpdate, passwordToUpdate);
            }
            case 5 -> {
                String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
                userDaoDb.editUserEmailById(idOfStudentToUpdate, emailToUpdate);
            }
            case 0 -> studentManagmentMenu();
        }

    }

    public void deleteStudentbyId() {
        int studentIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of student you want to delete: ");
        userDaoDb.deleteUserById(studentIdToRemove);
    }

    private void questsManagmentMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("(1) View quests table\n" +
                    "(2) Create quest\n" +
                    "(3) Update quest data\n" +
                    "(4) Delete quest\n" +
                    "(0) Quit");
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> questDAO.readQuestList();
                case 2 -> addQuest();
                case 3 -> updateQuest();
                case 4 -> deleteQuestById();
                case 0 -> isRunning = false;
                default -> System.out.println("Wrong input.");
            }


        }
    }

    public void addQuest() {
        String questTitle = InputTaker.takeStringInputWithMessage("Enter title of quest: ");
        String questDescription = InputTaker.takeStringInputWithMessage("Enter description of quest: ");
        int questCoins = InputTaker.takeIntInputWithMessage("Enter amount of coins it costs: ");
        String questImage = InputTaker.takeStringInputWithMessage("Enter image name: ");
        int questMentorId = InputTaker.takeIntInputWithMessage("Enter id of mentor: ");

        Quest questToAdd = new Quest(questTitle, questDescription, questCoins, questImage, questMentorId);
        questDAO.addQuest(questToAdd);

    }

    public void updateQuest() {
        questDAO.readAllQuestsOrderById();
        int idOfQuestToUpdate = InputTaker.takeIntInputWithMessage("Enter id of quest you want to edit: ");
        System.out.println("What do you want to edit? \n");
        System.out.println("(1) Title\n" +
                "(2) Description\n" +
                "(3) Amount of coins\n" +
                "(4) Id of mentor who created quest\n" +
                "(0) Back to the Quest Managment Menu");
        int option = InputTaker.takeIntInputWithMessage("Choose: ");
        switch (option) {
            case 1:
                String titleToUpdate = InputTaker.takeStringInputWithMessage("Enter new title: ");
                questDAO.updateQuestTitleById(idOfQuestToUpdate, titleToUpdate);
                break;
            case 2:
                String descriptionToUpdate = InputTaker.takeStringInputWithMessage("Enter new description: ");
                questDAO.updateQuestDescriptionById(idOfQuestToUpdate, descriptionToUpdate);
                break;
            case 3:
                int coinsToUpdate = InputTaker.takeIntInputWithMessage("Enter new amount of coins: ");
                questDAO.updateQuestCoinsById(idOfQuestToUpdate, coinsToUpdate);
                break;
            case 4:
                int idOfQuestMentorToUpdate = InputTaker.takeIntInputWithMessage("Enter new id of mentor: ");
                questDAO.updateQuestIdMentorById(idOfQuestToUpdate, idOfQuestMentorToUpdate);
            case 0:
                questsManagmentMenu();
            default:
                System.out.println("Wrong input!");
        }

    }

    public void deleteQuestById() {
        int questIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of quest you want to delete: ");
        questDAO.deleteQuestById(questIdToRemove);
    }

    private void awardsManagmentMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("(1) View awards table\n" +
                    "(2) Create award\n" +
                    "(3) Update award data\n" +
                    "(4) Delete award\n" +
                    "(0) Quit");
            int option = InputTaker.takeIntInputWithoutMessage();
            switch (option) {
                case 1 -> awardDAO.readAllAwardsOrderById();
                case 2 -> addAward();
                case 3 -> updateAward();
                case 4 -> deleteAwardById();
                case 0 -> isRunning = false;
                default -> System.out.println("Wrong input.");
            }


        }
    }

    public void addAward() {
        String awardTitle = InputTaker.takeStringInputWithMessage("Enter title of award: ");
        String awardDescription = InputTaker.takeStringInputWithMessage("Enter description of award: ");
        int awardPrice = InputTaker.takeIntInputWithMessage("Enter price of award: ");
        String awardImage = InputTaker.takeStringInputWithMessage("Enter image name: ");
        int awardCreatorId = InputTaker.takeIntInputWithMessage("Enter id of award creator: ");
        Date date = new Date();
        Award awardToAdd = new Award(awardTitle, awardDescription, awardPrice, awardImage, new Timestamp(date.getTime()), awardCreatorId);
        awardDAO.addAward(awardToAdd);


    }


    public void updateAward() {
        awardDAO.readAllAwardsOrderById();
        int idOfAwardToUpdate = InputTaker.takeIntInputWithMessage("Enter id of award you want to edit: ");
        System.out.println("What do you want to edit? \n");
        System.out.println("(1) Title\n" +
                "(2) Description\n" +
                "(3) Price\n" +
                "(4) Id of award creator\n" +
                "(0) Back to the Award Managment Menu");
        int option = InputTaker.takeIntInputWithMessage("Choose: ");
        switch (option) {
            case 1:
                String titleToUpdate = InputTaker.takeStringInputWithMessage("Enter new title: ");
                awardDAO.updateAwardTitleById(idOfAwardToUpdate, titleToUpdate);
                break;
            case 2:
                String descriptionToUpdate = InputTaker.takeStringInputWithMessage("Enter new description: ");
                awardDAO.updateAwardDescriptionById(idOfAwardToUpdate, descriptionToUpdate);
                break;
            case 3:
                int priceToUpdate = InputTaker.takeIntInputWithMessage("Enter new price: ");
                awardDAO.updateAwardPriceById(idOfAwardToUpdate, priceToUpdate);
                break;
            case 4:
                int idOfCreatorToUpdate = InputTaker.takeIntInputWithMessage("Enter new id of award creator: ");
                awardDAO.updateAwardCreatorIdById(idOfAwardToUpdate, idOfCreatorToUpdate);
            case 0:
                questsManagmentMenu();
            default:
                System.out.println("Wrong input!");
        }
    }

    public void deleteAwardById() {
        int awardIdToRemove = InputTaker.takeIntInputWithMessage("Enter id of award you want to delete: ");
        awardDAO.deleteAwardById(awardIdToRemove);
    }

    public void markStudentAchievedQuests() {

    }


}
