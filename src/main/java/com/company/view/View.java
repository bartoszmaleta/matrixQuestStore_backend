package com.company.view;

import com.company.dao.AwardDAO;
import com.company.dao.QuestDAO;
import com.company.dao.UserDaoDb;
import com.company.models.Award;
import com.company.models.Quest;
import com.company.models.users.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class View {
    public static void viewAllStudents() throws FileNotFoundException {
        // THIS WORKS BELOW WITH HASH
//        FileReader.printFromFile(location + "StudentsList");
//        List<User> newList = new UserDaoFromCSV().extractUsersFromListOfRecordsByRoleGiven("student");
        List<User> newList = new UserDaoDb().getListOfUsersOrderedByName();
        String[] headers = {"id", "name", "surname", "login", "password", "email", "role"};
        Object[][] data = new Object[newList.size()][headers.length];

        for (int i = 0; i < newList.size(); i++) {
            User user = newList.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getSurname();
            data[i][3] = user.getLogin();
//            data[i][2] = user.getPassword();
            data[i][4] = "*".repeat(user.getPassword().length());
            data[i][5] = user.getEmail();
            data[i][6] = user.getRole();
        }
        System.out.println(FlipTableConverters.fromObjects(headers, data));

        // THIS WORKS BELOW WITHOUT HASH
//        DataHandler.printFromFile(location + "AllStudents");
//        System.out.println(FlipTableConverters.fromIterable(students, User.class));
    }

    public static void viewAllQuests() throws FileNotFoundException {
        ArrayList<Quest> newList = new QuestDAO().readQuestList();
        String[] headers = {"id", "title", "description", "coins", "image", "mentor_id"};
        Object[][] data = new Object[newList.size()][headers.length];

        for (int i = 0; i < newList.size(); i++) {
            Quest quest = newList.get(i);
            data[i][0] = quest.getId();
            data[i][1] = quest.getTitle();
            data[i][2] = quest.getDescription();
            data[i][3] = quest.getPrice();
            data[i][4] = quest.getImageSrc();
            data[i][5] = quest.getMentorId();
        }
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public static void viewAllAwards() throws FileNotFoundException {
        ArrayList<Award> newList = new AwardDAO().readAwardList();
        String[] headers = {"id", "title", "description", "coins", "image", "creation_date", "creator_id"};
        Object[][] data = new Object[newList.size()][headers.length];

        for (int i = 0; i < newList.size(); i++) {
            Award award = newList.get(i);
            data[i][0] = award.getId();
            data[i][1] = award.getTitle();
            data[i][2] = award.getDescription();
            data[i][3] = award.getPrice();
            data[i][4] = award.getImageSrc();
            data[i][5] = award.getDataCreation();
            data[i][6] = award.getCreatorId();
        }
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }
}
