package com.company.dao;

import java.sql.*;

public class ConnectionFactory {
    private final String databaseUrl;
    private final String jdbcDriver;
    private final String userLogin;
    private final String userPassword;

    public ConnectionFactory() {
        this.databaseUrl = "jdbc:postgresql://ec2-52-31-94-195.eu-west-1.compute.amazonaws.com:5432/dfare0vp739v70";
        this.jdbcDriver = "org.postgresql.Driver";
        this.userLogin = "gnoujqtgpyxews";
        this.userPassword = "c05d60807f1a76c1447fcafb2906941992b1e529dfd0f6c468978e4ce0661ef7";
    }

    public ConnectionFactory(String databaseUrl, String jdbcDriver, String userLogin, String userPassword) {
        this.databaseUrl = databaseUrl;
        this.jdbcDriver = jdbcDriver;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    protected Connection con;
    protected Statement stmt = null;
    protected ResultSet rs = null;

    public Connection getConnection() {
        try {
            Class.forName(this.jdbcDriver);
            this.con = DriverManager.getConnection(this.databaseUrl, this.userLogin, this.userPassword);
        } catch (SQLException e) {
            System.out.println("Error! Cannot connect with the database.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error! Cannot find JDBC Driver!");
        }
        System.out.println("Connection returned");
        return this.con;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        this.con = connectionFactory.getConnection();
        this.con = this.getConnection();
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
        this.con = this.getConnection();
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
        } catch (SQLException e) {
            System.out.println("Error! Can't connect with the database.");
        }
    }

}
