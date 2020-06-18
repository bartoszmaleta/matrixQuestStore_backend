package com.company.service;

import com.company.dao.AwardDAO;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.User;
import com.company.view.View;

import java.io.FileNotFoundException;
import java.util.List;

public class AdminService extends EmployeeService {
    private final UserDao userDao;
    private final AwardDAO awardDAO;
    private final UserDaoDb userDaoDb;

    public AdminService() {
        userDao = new UserDaoDb();
        this.awardDAO = new AwardDAO();
        this.userDaoDb = new UserDaoDb();
    }

    public void displayAllUsers() throws FileNotFoundException {
        List<User> users = this.userDao.getAllElements();
        View.allUsersByList(users);
    }

    public void deleteMentor(int mentorToRemoveById) {
        this.userDaoDb.delete(mentorToRemoveById);
    }

    public void displayAllMentors() throws FileNotFoundException {
        List<User> mentors = this.userDaoDb.getMentors();
        View.allMentors(mentors);
    }


}
