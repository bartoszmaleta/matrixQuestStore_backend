package com.company.service;

import com.company.dao.*;
import com.company.model.Award;
import com.company.model.Quest;
import com.company.model.Transaction;
import com.company.model.user.User;
import com.company.view.AwardsView;
import com.company.view.QuestsView;
import com.company.view.StudentView;
import com.github.tomaslanger.chalk.Chalk;

import java.io.FileNotFoundException;
import java.util.List;

public class StudentService {
    private final StudentView studentView;
    private final QuestDao questDao;
    private final TransactionDao transactionDao;
    private final UserDao userDao;
    private final AwardDao awardDao;

    public StudentService() {
        this.studentView = new StudentView();
        this.questDao = new QuestDaoDb();
        this.transactionDao = new TransactionDaoDb();
        this.userDao = new UserDaoDb();
        this.awardDao = new AwardDaoDb();
    }

    public void displayMenu() {
        studentView.showMenu();
    }

    public void displayMyAwards(User user) {
        List<Award> awards = this.awardDao.getAwardsByUser(user);
        AwardsView.allAwardsByList(awards);
        //TODO:
    }

    public void buyAward(User user) {
        //TODO:

    }

    public void displayMyTransactions(User user) throws FileNotFoundException {
        List<Transaction> transactions = transactionDao.getMyTransactions(user);
        StudentView.allTransactionsFromList(transactions);
        //TODO:
    }

    public void displayMyProfile(User user) {
        String myProfileText = ("My profile:\n"
                + "Name: " + user.getName()
                + "Surname: " + user.getSurname()
                + "Login: " + user.getLogin()
                + "Email: " + user.getEmail()
                + "Role: " + user.getRole()
                + "Coins: ???"); // TODO

        System.out.println(Chalk.on(myProfileText).bold().cyan());
    }

    public void displayQuests() throws FileNotFoundException {
        List<Quest> quests = this.questDao.getAllElements();
        QuestsView.allQuestsByList(quests);
    }
}
