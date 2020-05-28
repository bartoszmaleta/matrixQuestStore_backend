package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DAO {
    public void Table() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/dcmgt3tfcp4n6o",
                            "tilcavmrsuhbzj", "37e3925b366710ece9a679ad72d401e74bc6bb4ed1239676aaffef00ed27fc52");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String surname  = rs.getString("surname");
                String  login = rs.getString("login");
                String  password = rs.getString("password");
                String  email = rs.getString("email");
                int  role_id = rs.getInt("role_id");
                int  user_detail_id = rs.getInt("user_detail_id");

                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "SURNAME = " + surname );
                System.out.println( "Login = " + login );
                System.out.println( "Password = " + password );
                System.out.println( "email = " + email );
                System.out.println( "Role = " + role_id );
                System.out.println( "User Detail = " + user_detail_id );

                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}