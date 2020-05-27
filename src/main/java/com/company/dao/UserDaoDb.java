package com.company.dao;

import com.company.models.users.User;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDaoDb {
    public User readUserByEmailAndPassword() {

        Connection c = null;
        User newUser = new User();
        try {
            // TODO: why twice prints this:???
//            System.out.println("\nI am in readUserByNameAndPassword\n");
            ConnectionFactory ds = new ConnectionFactory();
            ResultSet rs = ds.executeQuery("SELECT * FROM Users WHERE \"Name\" = '"+userName+"' AND \"Password\" = '"+userPassword+"';");
            if (rs.next() && rs.getString("Name").equals(userName)) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                int isAdmin = rs.getInt("IsAdmin");

                newUser.setId(id);
                newUser.setName(name);
                newUser.setPassword(password);
                newUser.setEmail(email);
                newUser.setIsAdmin(isAdmin);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
        }
        return newUser;
        return null;
    }
}
