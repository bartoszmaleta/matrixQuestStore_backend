package com.company.dao;

import java.sql.Connection;

public class TestMain {
    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection con = cf.getConnection();
    }
}
