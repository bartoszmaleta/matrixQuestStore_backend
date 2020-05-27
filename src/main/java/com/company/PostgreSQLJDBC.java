package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLJDBC {
    public static void main(String args[]) {
//        https://www.tutorialspoint.com/postgresql/postgresql_java.htm
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/dcmgt3tfcp4n6o",
                            "tilcavmrsuhbzj", "37e3925b366710ece9a679ad72d401e74bc6bb4ed1239676aaffef00ed27fc52");

//            c = DriverManager
//                    .getConnection("jdbc:postgresql://localhost:5432/people",
//                            "postgres", "admin");

//            Statement stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM friends;" );
//
//            while (rs.next() ) {
//                int friend_id = rs.getInt("friend_id");
//                String firstName = rs.getString("first_name");
//                int age = rs.getInt("age");
//                System.out.printf(friend_id + " " + firstName + " " + age + "\n");
//            }
//            rs.close();
//            stmt.close();
//            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}