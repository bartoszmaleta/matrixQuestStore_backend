package com.company.view;

import com.company.dao.UserDaoDb;
import com.company.models.users.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
}
