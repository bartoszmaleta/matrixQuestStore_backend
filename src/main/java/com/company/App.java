package com.company;

import com.company.dao.QuestDAOFromDB;
import com.company.models.Quest;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        DBConnector db = new DBConnector();
        db.connectToDB();
        QuestDAOFromDB questDAOFromDB = new QuestDAOFromDB(db);
        Quest questToAdd = new Quest(10, "Test Quest", "Testing if quest will be added to db", 15, "img-src", 2);
//        questDAOFromDB.addQuest(questToAdd);
//        questDAOFromDB.deleteQuestById(11);
            questDAOFromDB.viewQuests(questDAOFromDB.readQuestList());

    }
}
