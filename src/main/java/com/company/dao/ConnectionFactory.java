package com.company.dao;

import java.sql.*;

public class ConnectionFactory {
    private String databaseUrl;
    private String jdbcDriver;
    private String userLogin;
    private String userPassword;

    public ConnectionFactory() {
        // MAIN DB
                this.databaseUrl = "jdbc:postgresql://ec2-52-31-94-195.eu-west-1.compute.amazonaws.com/dfare0vp739v70";
        this.jdbcDriver = "org.postgresql.Driver";
        this.userLogin = "gnoujqtgpyxews";
        this.userPassword = "c05d60807f1a76c1447fcafb2906941992b1e529dfd0f6c468978e4ce0661ef7";
        // BACKUP DB
//        this.databaseUrl = "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc";
//        this.jdbcDriver = "org.postgresql.Driver";
//        this.userLogin = "pirqathgcgzhbg";
//        this.userPassword = "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed";
    }

//    public ConnectionFactory(String databaseUrl, String jdbcDriver, String userLogin, String userPassword) {
//        this.databaseUrl = databaseUrl;
//        this.jdbcDriver = jdbcDriver;
//        this.userLogin = userLogin;
//        this.userPassword = userPassword;
//    }

    protected Connection con;
    //        protected Statement stmt = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;

    public Connection getConnection() {
        try {
            Class.forName(this.jdbcDriver);
            this.con = DriverManager.getConnection(this.databaseUrl, userLogin, userPassword);
        } catch (SQLException e) {
            System.out.println("Error! Cannot connect with the database." );
        } catch (ClassNotFoundException e) {
            System.out.println("Error! Cannot find JDBC Driver!");
        }
        System.out.println("Connection returned");
        return this.con;
    }

    public ResultSet executeQuery(String sql) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.con = connectionFactory.getConnection();
        try {
            this.stmt = this.con.createStatement();
            this.rs = this.stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error! Cannot execute query!");
            e.printStackTrace();
        }
        return this.rs;
    }

    public void updateQuery(String sql) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.con = connectionFactory.getConnection();
        try {
            stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error! Cannot update query!");
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        try {
            // TODO:
            // REMEMBER TO: close stmt!!!!
//                this.stmt.close();
            this.con.close();
            System.out.println("Connection closed");
        }catch (SQLException e) {
            System.out.println("Error! Can't connect with the database." );
        }
    }

}