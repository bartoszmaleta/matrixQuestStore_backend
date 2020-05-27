package com.company.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
        cf.close();
    }
}
