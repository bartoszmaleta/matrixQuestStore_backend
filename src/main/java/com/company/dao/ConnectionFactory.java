package com.company.dao;

import java.sql.*;

public class ConnectionFactory {
        private String databaseUrl;
        private String jdbcDriver;

        public ConnectionFactory() {
            this.databaseUrl = "jdbc:sqlite:src/main/resources/sqliteDB/AllteregoDB";
            this.jdbcDriver = "org.sqlite.JDBC";
        }

        protected Connection con;
        protected Statement stmt = null;
        protected ResultSet rs = null;

        public Connection getConnection() {
            try {
                Class.forName(this.jdbcDriver);
                this.con = DriverManager.getConnection(this.databaseUrl);
            } catch (SQLException e) {
                System.out.println("Error! Cannot connect with the database." );
            } catch (ClassNotFoundException e) {
                System.out.println("Error! Cannot find JDBC Driver!");
            }
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
                this.stmt.close();
                this.con.close();
            }catch (SQLException e) {
                System.out.println("Error! Can't connect with the database." );
            }
        }

    }
