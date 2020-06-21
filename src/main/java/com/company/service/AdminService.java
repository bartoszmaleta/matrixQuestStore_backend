package com.company.service;

import com.company.dao.AwardDaoDb;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.User;
import com.company.view.View;

import java.io.FileNotFoundException;
import java.util.List;

public class AdminService extends EmployeeService {
    private final UserDao userDao;
    private final AwardDaoDb awardDAO;
    private final UserDaoDb userDaoDb;

    public AdminService() {
        userDao = new UserDaoDb();
        this.awardDAO = new AwardDaoDb();
        this.userDaoDb = new UserDaoDb();
    }

    public void displayAllUsers() throws FileNotFoundException {
        List<User> users = this.userDao.getAllElements();
        View.allUsersByList(users);
    }

    public void deleteMentor(int mentorToRemoveById) {
        this.userDaoDb.delete(mentorToRemoveById);
    }



    public void updateMentorsUsername(int mentorsId) {
        String nameToUpdate = InputTaker.takeStringInputWithMessage("Enter new name: ");
        this.userDaoDb.updateUserNameById(mentorsId, nameToUpdate);
    }

    public void updateMentorsSurname(int idOfMentorToUpdate) {
        String surnameToUpdate = InputTaker.takeStringInputWithMessage("Enter new surname: ");
        this.userDaoDb.updateUserSurnameById(idOfMentorToUpdate, surnameToUpdate);
    }

    public void updateMentorsLogin(int idOfMentorToUpdate) {
        String loginToUpdate = InputTaker.takeStringInputWithMessage("Enter new login: ");
        this.userDaoDb.updateUserLoginById(idOfMentorToUpdate, loginToUpdate);
    }

    public void updateMentorsPassword(int idOfMentorToUpdate) {
        String passwordToUpdate = InputTaker.takeStringInputWithMessage("Enter new password: ");
        this.userDaoDb.updateUserPasswordById(idOfMentorToUpdate, passwordToUpdate);
    }

    public void updateMentorsEmail(int idOfMentorToUpdate) {
        String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
        this.userDaoDb.editUserEmailById(idOfMentorToUpdate, emailToUpdate);
    }

    public void displayMentorsOptions() {
        View.updateMentorModes();
    }
}
