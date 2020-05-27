package com.company.dao;

import com.company.DBConnector;
import com.company.models.Quest;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestDAOFromDB {
    Connection c;
    Statement st;
    ArrayList listOfQuests;

    public QuestDAOFromDB(DBConnector dbConnector) {
        this.c = dbConnector.getConnection();
        this.st = dbConnector.getSt();
    }

    public void addQuest(Quest quest) {

    }

    public void updateQuest(Quest quest) {

    }

    public void deleteQuestById(int id) {

    }

    public ArrayList<Quest> readAllQuests() {
        return null;
    }
}
