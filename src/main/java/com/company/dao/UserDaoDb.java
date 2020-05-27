package com.company.dao;

import com.company.models.users.*;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDaoDb {

    public User readUserByEmailAndPassword(String userEmail, String userPassword) {

        Connection c = null;
        User newUser;

        try {
            System.out.println("\nI am in readUserByNameAndPassword\n");
            ConnectionFactory connectionFactory = new ConnectionFactory();
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM \"Users\" WHERE \"email\" = '"+userEmail+"' AND \"password\" = '"+userPassword+"';");

            if (rs.next() && (rs.getInt("role_id") == 1)) {
                newUser = new Admin();

                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int role_id = rs.getInt("role_id");

                newUser.setId(id);
                newUser.setLogin(login);
                newUser.setPassword(password);
                newUser.setEmail(email);
                newUser.setRoleEnum(role_id);

                // TODO: where close()?????
                connectionFactory.close();
                rs.close();

                return newUser;

            } else if (rs.next() && (rs.getInt("role_id") == 2)) {
//                newUser = new Mentor();
            } else if (rs.next() && (rs.getInt("role_id") == 3)) {
//                newUser = new Student();
            } else {
                System.out.println("No user");
            }

            // TODO: where close()?????
//            connectionFactory.close();
//            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
        }
        return null;
    }
}
