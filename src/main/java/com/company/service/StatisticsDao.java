package com.company.service;

import com.company.dao.AwardDaoDb;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.User;
import com.company.view.UserView;

import java.io.FileNotFoundException;
import java.util.List;

public class StatisticsDao extends EmployeeService {
    private final UserDao userDao;
    private final AwardDaoDb awardDAO;

    public StatisticsDao() {
        this.userDao = new UserDaoDb();
        this.awardDAO = new AwardDaoDb();
    }

    public void displayAllUsers() throws FileNotFoundException {
        List<User> users = this.userDao.getAllElements();
        UserView.allUsersByList(users);
    }

//    public void deleteMentor(int mentorToRemoveById) {
//        this.userDaoDb.delete(mentorToRemoveById);
//    }

    public void deleteUserById(int id) {
        this.userDao.delete(id);
    }

//

    public void updateMentorsUsername(int mentorsId) {
        String nameToUpdate = InputTaker.takeStringInputWithMessage("Enter new name: ");
        this.userDao.updateUserNameById(mentorsId, nameToUpdate);
    }

    public void updateMentorsSurname(int idOfMentorToUpdate) {
        String surnameToUpdate = InputTaker.takeStringInputWithMessage("Enter new surname: ");
        this.userDao.updateUserSurnameById(idOfMentorToUpdate, surnameToUpdate);
    }

    public void updateMentorsLogin(int idOfMentorToUpdate) {
        String loginToUpdate = InputTaker.takeStringInputWithMessage("Enter new login: ");
        this.userDao.updateUserLoginById(idOfMentorToUpdate, loginToUpdate);
    }

    public void updateMentorsPassword(int idOfMentorToUpdate) {
        String passwordToUpdate = InputTaker.takeStringInputWithMessage("Enter new password: ");
        this.userDao.updateUserPasswordById(idOfMentorToUpdate, passwordToUpdate);
    }

    public void updateMentorsEmail(int idOfMentorToUpdate) {
        String emailToUpdate = InputTaker.takeStringInputWithMessage("Enter new email: ");
        this.userDao.editUserEmailById(idOfMentorToUpdate, emailToUpdate);
    }

    public void displayMentorsOptions() {
        UserView.updateMentorModes();
    }
}
