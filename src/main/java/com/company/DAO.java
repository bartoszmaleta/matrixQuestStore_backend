package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DAO {
    public void CreateTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/dcmgt3tfcp4n6o",
                            "tilcavmrsuhbzj", "37e3925b366710ece9a679ad72d401e74bc6bb4ed1239676aaffef00ed27fc52");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void InsertTable() {
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
            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    public void TableView() {
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
            ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );

            DBTablePrinter.printTable(c, "users");
            while ( rs.next() ) {

                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));


                /*int id = rs.getInt("id");
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
*/
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

    public void ViewTable() {
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
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
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