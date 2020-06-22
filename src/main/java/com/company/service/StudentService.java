package com.company.service;

import com.company.dao.QuestDao;
import com.company.dao.QuestDaoDb;
import com.company.dao.TransactionDao;
import com.company.dao.TransactionDaoDb;
import com.company.models.Quest;
import com.company.models.Transaction;
import com.company.models.users.Student;
import com.company.models.users.User;
import com.company.view.QuestsView;
import com.company.view.StudentView;
import com.github.tomaslanger.chalk.Chalk;

import java.io.FileNotFoundException;
import java.util.List;

public class StudentService {
    private final StudentView studentView;
    private final QuestDao questDao;
    private final TransactionDao transactionDao;

    public StudentService() {
        this.studentView = new StudentView();
        this.questDao = new QuestDaoDb();
        this.transactionDao = new TransactionDaoDb();
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
