package com.company.view;

import com.company.model.user.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.FileNotFoundException;
import java.util.List;

public class UserView {

    public static void allMentors(List<User> mentors) throws FileNotFoundException {
        String[] headers = {"id", "name", "surname", "login", "password", "email", "role", "avatar path"};
        Object[][] data = new Object[mentors.size()][headers.length];
        int maxLengthOfPassword = getMaxLengthOfPassword(mentors);

        for (int i = 0; i < mentors.size(); i++) {
            User user = mentors.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getSurname();
            data[i][3] = user.getLogin();
            data[i][4] = "*".repeat(maxLengthOfPassword);
            data[i][5] = user.getEmail();
            data[i][6] = user.getRole();
            data[i][7] = user.getAvatarSource();
        }
        System.out.println("All Mentors");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public static void allStudents(List<User> students) throws FileNotFoundException {
        // THIS WORKS BELOW WITH HASH
//        FileReader.printFromFile(location + "StudentsList");
//        List<User> newList = new UserDaoFromCSV().extractUsersFromListOfRecordsByRoleGiven("student");
//        List<User> students = new UserDaoDb().getStudents();

        String[] headers = {"id", "name", "surname", "login", "password", "email", "role", "avatar path"};
        Object[][] data = new Object[students.size()][headers.length];

        int maxLengthOfPassword = getMaxLengthOfPassword(students);

        for (int i = 0; i < students.size(); i++) {
            User user = students.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getSurname();
            data[i][3] = user.getLogin();
            data[i][4] = "*".repeat(maxLengthOfPassword);
            data[i][5] = user.getEmail();
            data[i][6] = user.getRole();
            data[i][7] = user.getAvatarSource();
        }
        System.out.println("All Students");
        System.out.println(FlipTableConverters.fromObjects(headers, data));

        // THIS WORKS BELOW WITHOUT HASH
//        DataHandler.printFromFile(location + "AllStudents");
//        System.out.println(FlipTableConverters.fromIterable(students, User.class));
    }

    private static int getMaxLengthOfPassword(List<User> students) {
        int maxLengthOfPassword = 0;
        for (User user : students) {
            int passLen = user.getPassword().length();
            if (passLen > maxLengthOfPassword) {
                maxLengthOfPassword = passLen;
            }
        }
        return maxLengthOfPassword;
    }

    public static void allUsersByList(List<User> users) throws FileNotFoundException {
        String[] headers = {"id", "name", "surname", "login", "password", "email", "role", "avatar path"};
        Object[][] data = new Object[users.size()][headers.length];

        int maxLengthOfPassword = getMaxLengthOfPassword(users);

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getName();
            data[i][2] = user.getSurname();
            data[i][3] = user.getLogin();
            data[i][4] = "*".repeat(maxLengthOfPassword);
            data[i][5] = user.getEmail();
            data[i][6] = user.getRole();
            data[i][7] = user.getAvatarSource();
        }
        System.out.println("All Users");
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

                "(12) Display transactions statistics by student\n" +
                "(13) Display quests count by mentor\n" +
                "(14) Display awards count by mentor\n" +

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
                // TODO: edit avatar path
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
                "(6) Avatar path\n" +
                "(0) Back to Student Managment Menu");
    }

}
