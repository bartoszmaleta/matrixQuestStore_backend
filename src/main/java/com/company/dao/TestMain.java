package com.company.dao;

import com.company.models.users.User;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException {
//        ConnectionFactory cf = new ConnectionFactory();
//        Connection con = cf.getConnection();

        User testUser = new UserDaoDb().readUserByEmailAndPassword("test@test.pl", "testPassword");
        System.out.println(testUser.getPassword());
//        cf.close();
    }
}
