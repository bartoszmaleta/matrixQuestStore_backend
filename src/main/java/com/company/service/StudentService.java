package com.company.service;

import com.company.dao.QuestDao;
import com.company.dao.QuestDaoDb;
import com.company.models.Quest;
import com.company.models.users.User;
import com.company.view.QuestsView;
import com.company.view.StudentView;

import java.io.FileNotFoundException;
import java.util.List;

public class StudentService {
    private final StudentView studentView;
    private final QuestDao questDao;

    public StudentService() {
        this.studentView = new StudentView();
        this.questDao = new QuestDaoDb();
    }

    public void displayMenu() {
        studentView.showMenu();
    }

    public void displayMyAwards(User user) {
        //TODO:
    }

    public void buyAward(User user) {
        //TODO:

    }

    public void displayMyTransactions(User user) {
        //TODO:
    }

    public void displayMyProfile(User user) {
        //TODO:
    }

    public void displayQuests() throws FileNotFoundException {
        List<Quest> quests = this.questDao.getAllElements();
        QuestsView.allQuestsByList(quests);
    }
}
