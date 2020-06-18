package com.company.view;

import com.company.dao.AwardDAO;
import com.company.dao.UserDaoDb;
import com.company.models.Award;
import com.company.models.Quest;
import com.company.models.users.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.FileNotFoundException;
import java.util.List;

public class View {

//    public static void viewAllMentors() throws FileNotFoundException {
//        List<User> newList = new UserDaoDb().getMentors();
//        String[] headers = {"id", "name", "surname", "login", "password", "email", "role"};
//        Object[][] data = new Object[newList.size()][headers.length];
//
//        for (int i = 0; i < newList.size(); i++) {
//            User user = newList.get(i);
//            data[i][0] = user.getId();
//            data[i][1] = user.getName();
//            data[i][2] = user.getSurname();
//            data[i][3] = user.getLogin();
////            data[i][2] = user.getPassword();
//            data[i][4] = "*".repeat(user.getPassword().length());
//            data[i][5] = user.getEmail();
//            data[i][6] = user.getRole();
//        }
//        System.out.println("All Mentors");
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

    public static void allMentors(List<User> mentors) throws FileNotFoundException {
        String[] headers = {"id", "name", "surname", "login", "password", "email", "role"};
        Object[][] data = new Object[mentors.size()][headers.length];

        for (int i = 0; i < mentors.size(); i++) {
            User user = mentors.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getSurname();
            data[i][3] = user.getLogin();
//            data[i][2] = user.getPassword();
            data[i][4] = "*".repeat(user.getPassword().length());
            data[i][5] = user.getEmail();
            data[i][6] = user.getRole();
        }
        System.out.println("All Mentors");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public static void viewAllStudents() throws FileNotFoundException {
        // THIS WORKS BELOW WITH HASH
//        FileReader.printFromFile(location + "StudentsList");
//        List<User> newList = new UserDaoFromCSV().extractUsersFromListOfRecordsByRoleGiven("student");
        List<User> newList = new UserDaoDb().getStudents();
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
        System.out.println("All Students");
        System.out.println(FlipTableConverters.fromObjects(headers, data));

        // THIS WORKS BELOW WITHOUT HASH
//        DataHandler.printFromFile(location + "AllStudents");
//        System.out.println(FlipTableConverters.fromIterable(students, User.class));
    }

    public static void allStudents(List<User> students) throws FileNotFoundException {
        // THIS WORKS BELOW WITH HASH
//        FileReader.printFromFile(location + "StudentsList");
//        List<User> newList = new UserDaoFromCSV().extractUsersFromListOfRecordsByRoleGiven("student");
//        List<User> students = new UserDaoDb().getStudents();

        String[] headers = {"id", "name", "surname", "login", "password", "email", "role"};
        Object[][] data = new Object[students.size()][headers.length];

        for (int i = 0; i < students.size(); i++) {
            User user = students.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getSurname();
            data[i][3] = user.getLogin();
//            data[i][2] = user.getPassword();
            data[i][4] = "*".repeat(user.getPassword().length());
            data[i][5] = user.getEmail();
            data[i][6] = user.getRole();
        }
        System.out.println("All Students");
        System.out.println(FlipTableConverters.fromObjects(headers, data));

        // THIS WORKS BELOW WITHOUT HASH
//        DataHandler.printFromFile(location + "AllStudents");
//        System.out.println(FlipTableConverters.fromIterable(students, User.class));
    }

//    public static void viewAllUsers() throws FileNotFoundException {
//        List<User> newList = new UserDaoDb().readAllUsers();
//        String[] headers = {"id", "name", "surname", "login", "password", "email", "role"};
//        Object[][] data = new Object[newList.size()][headers.length];
//
//        for (int i = 0; i < newList.size(); i++) {
//            User user = newList.get(i);
//            data[i][0] = user.getId();
//            data[i][1] = user.getName();
//            data[i][2] = user.getSurname();
//            data[i][3] = user.getLogin();
////            data[i][2] = user.getPassword();
//            data[i][4] = "*".repeat(user.getPassword().length());
//            data[i][5] = user.getEmail();
//            data[i][6] = user.getRole();
//        }
//        System.out.println("All Users");
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

    public static void allUsersByList(List<User> users) throws FileNotFoundException {
        String[] headers = {"id", "name", "surname", "login", "password", "email", "role"};
        Object[][] data = new Object[users.size()][headers.length];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getSurname();
            data[i][3] = user.getLogin();
//            data[i][2] = user.getPassword();
            data[i][4] = "*".repeat(user.getPassword().length());
            data[i][5] = user.getEmail();
            data[i][6] = user.getRole();
        }
        System.out.println("All Users");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

//    public static void viewAllQuests() throws FileNotFoundException {
//        List<Quest> newList = new QuestDAO().readQuestList();
//        String[] headers = {"id", "title", "description", "coins", "image", "mentor_id"};
//        Object[][] data = new Object[newList.size()][headers.length];
//
//        for (int i = 0; i < newList.size(); i++) {
//            Quest quest = newList.get(i);
//            data[i][0] = quest.getId();
//            data[i][1] = quest.getTitle();
//            data[i][2] = quest.getDescription();
//            data[i][3] = quest.getPrice();
//            data[i][4] = quest.getImageSrc();
//            data[i][5] = quest.getMentorId();
//        }
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

//    public static void viewAllQuestsWithMentors() throws FileNotFoundException {
//        List<Quest> newList = new QuestDAO().readQuestListWithMentors();
//        String[] headers = {"id", "title", "description", "coins", "image", "mentor"};
//        Object[][] data = new Object[newList.size()][headers.length];
//
//        for (int i = 0; i < newList.size(); i++) {
//            Quest quest = newList.get(i);
//            data[i][0] = quest.getId();
//            data[i][1] = quest.getTitle();
//            data[i][2] = quest.getDescription();
//            data[i][3] = quest.getPrice();
//            data[i][4] = quest.getImageSrc();
//            data[i][5] = quest.getMentorNameAndSurname();
//        }
//        System.out.println("All quests with mentors");
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

    public static void allQuestsByList(List<Quest> newList) throws FileNotFoundException {
        System.out.println("all quests by list - view");

        //        List<Quest> newList = new QuestDAO().readQuestListWithMentors();
        String[] headers = {"id", "title", "description", "coins", "image", "mentor"};
        Object[][] data = new Object[newList.size()][headers.length];

        for (int i = 0; i < newList.size(); i++) {
            Quest quest = newList.get(i);
            data[i][0] = quest.getId();
            data[i][1] = quest.getTitle();
            data[i][2] = quest.getDescription();
            data[i][3] = quest.getPrice();
            data[i][4] = quest.getImageSrc();
            data[i][5] = quest.getMentorNameAndSurname();
        }
        System.out.println("All quests with mentors");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

//    public static void allQuestsByListTestGenerics(List<Quest> newList) throws FileNotFoundException {
//        String[] headers = {"id", "title", "description", "coins", "image", "mentor"};
//        Object[][] data = new Object[newList.size()][headers.length];
//
//        for (int i = 0; i < newList.size(); i++) {
//            Quest quest = newList.get(i);
//            data[i][0] = quest.getId();
//            data[i][1] = quest.getTitle();
//            data[i][2] = quest.getDescription();
//            data[i][3] = quest.getPrice();
//            data[i][4] = quest.getImageSrc();
//            data[i][5] = quest.getMentorNameAndSurname();
//        }
//        System.out.println("All quests with mentors");
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

//    public static void viewAllAwards() throws FileNotFoundException {
//        List<Award> newList = new AwardDAO().readAwardList();
//        String[] headers = {"id", "title", "description", "coins", "image", "creation_date", "creator_id"};
//        Object[][] data = new Object[newList.size()][headers.length];
//
//        for (int i = 0; i < newList.size(); i++) {
//            Award award = newList.get(i);
//            data[i][0] = award.getId();
//            data[i][1] = award.getTitle();
//            data[i][2] = award.getDescription();
//            data[i][3] = award.getPrice();
//            data[i][4] = award.getImageSrc();
//            data[i][5] = award.getDataCreation();
//            data[i][6] = award.getMentorId();
//        }
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

//    public static void viewAllAwardsFromList(List<Award> awardsList) throws FileNotFoundException {
//        String[] headers = {"id", "title", "description", "coins", "image", "creation_date", "creator_id"};
//        Object[][] data = new Object[awardsList.size()][headers.length];
//
//        for (int i = 0; i < awardsList.size(); i++) {
//            Award award = awardsList.get(i);
//            data[i][0] = award.getId();
//            data[i][1] = award.getTitle();
//            data[i][2] = award.getDescription();
//            data[i][3] = award.getPrice();
//            data[i][4] = award.getImageSrc();
//            data[i][5] = award.getDataCreation();
//            data[i][6] = award.getMentorId();
//        }
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

//    public static void viewAllAwardsWithMentors() {
//        List<Award> newList = new AwardDAO().readAwardListWithMentors();
//        String[] headers = {"id", "title", "description", "coins", "image", "creation_date", "creator"};
//        Object[][] data = new Object[newList.size()][headers.length];
//
//        for (int i = 0; i < newList.size(); i++) {
//            Award award = newList.get(i);
//            data[i][0] = award.getId();
//            data[i][1] = award.getTitle();
//            data[i][2] = award.getDescription();
//            data[i][3] = award.getPrice();
//            data[i][4] = award.getImageSrc();
//            data[i][5] = award.getDataCreation();
//            data[i][6] = award.getMentorNameAndSurname();
//        }
//        System.out.println("All awards with mentors");
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }

    public static void allAwardsByList(List<Award> awards) {
//        List<Award> newList = new AwardDAO().readAwardListWithMentors();
        String[] headers = {"id", "title", "description", "coins", "image", "creation_date", "creator"};
        Object[][] data = new Object[awards.size()][headers.length];

        for (int i = 0; i < awards.size(); i++) {
            Award award = awards.get(i);
            data[i][0] = award.getId();
            data[i][1] = award.getTitle();
            data[i][2] = award.getDescription();
            data[i][3] = award.getPrice();
            data[i][4] = award.getImageSrc();
            data[i][5] = award.getDataCreation();
            data[i][6] = award.getMentorNameAndSurname();
        }
        System.out.println("All awards with mentors");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public static void adminMenu() {
        System.out.println("\n(1) Display all users\n\n" +
                "(2) Display all mentors\n" +
                "(3) Add mentor\n" +
                "(4) Delete mentor\n" +
                "(5) Edit mentor\n\n" +

                "(6) Display all modules\n" +
                "(7) Add module\n" +
                "(8) Delete module\n" +
                "(9) Edit module\n\n" +

                "(10) Display all students\n" +
                "(11) My profile\n" +
                "(0) Quit");
    }

    public static void updateMentorModes() {
        System.out.println("What do you want to edit? \n");
        System.out.println("" +
                "(1) Name\n" +
                "(2) Surname\n" +
                "(3) Login\n" +
                "(4) Password\n" +
                "(5) Email\n" +
                "(0) Back to Admin Menu");

    }

    public static void mentorMenu() {
        System.out.println("(1) Student managment\n" +
                "(2) Quest managment\n" +
                "(3) Awards managment\n" +
                "(4) My profile\n" +
                "(0) Quit");
    }

    public static void studentManagmentMenu() {
        System.out.println("\n(1) View students table\n" +
                "(2) Create student\n" +
                "(3) Update student data\n" +
                "(4) Delete student\n" +
                "(5) View students wallets\n" +
                "(0) Quit");
    }

    public static void updateStudentModes() {
        System.out.println("What do you want to edit? \n");
        System.out.println("" +
                "(1) Name\n" +
                "(2) Surname\n" +
                "(3) Login\n" +
                "(4) Password\n" +
                "(5) Email\n" +
                "(0) Back to Student Managment Menu");
    }

    public static void awardsManagmentMenu() {
        System.out.println("(1) View awards table\n" +
                "(2) Create award\n" +
                "(3) Update award data\n" +
                "(4) Delete award\n" +
                "(0) Quit");
    }

    public static void questManagmentMenu() {
        System.out.println("(1) View quests table\n" +
                "(2) Create quest\n" +
                "(3) Update quest data\n" +
                "(4) Delete quest\n" +
                "(5) View my quests\n" +
                "(0) Quit");
    }

    public static void updateQuestModes() {
        System.out.println("What do you want to edit? \n");
        System.out.println("(1) Title\n" +
                "(2) Description\n" +
                "(3) Amount of coins\n" +
                "(4) Id of mentor who created quest\n" +
                "(0) Back to the Quest Managment Menu");
    }

    public static void updateAwardModes() {
        System.out.println("What do you want to edit? \n");
        System.out.println("(1) Title\n" +
                "(2) Description\n" +
                "(3) Price\n" +
                "(4) Id of award creator\n" +
                "(0) Back to the Award Managment Menu");
    }



}
