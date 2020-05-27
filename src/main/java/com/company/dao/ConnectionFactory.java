package com.company.dao;

import java.sql.*;

public class ConnectionFactory {
        private String databaseUrl;
        private String jdbcDriver;
        private String userLogin;
        private String userPassword;

        public ConnectionFactory() {
            this.databaseUrl = "jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/dcmgt3tfcp4n6o";
            this.jdbcDriver = "org.postgresql.Driver";
            this.userLogin = "tilcavmrsuhbzj";
            this.userPassword = "37e3925b366710ece9a679ad72d401e74bc6bb4ed1239676aaffef00ed27fc52";
        }

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
            ConnectionFactory dbsqlite = new ConnectionFactory();
            this.con = dbsqlite.getConnection();
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
                // REMEMBER TO: close stmt!!!!
//                this.stmt.close();
                this.con.close();
                System.out.println("Connection closed");
            }catch (SQLException e) {
                System.out.println("Error! Can't connect with the database." );
            }
        }

    }
