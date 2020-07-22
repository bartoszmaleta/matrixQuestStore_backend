package com.company.dao.DaoConnection;

public class ConnectionCredentials {
    public static final String DATABASE_URL = "jdbc:postgresql://ec2-52-31-94-195.eu-west-1.compute.amazonaws.com:5432/dfare0vp739v70";
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String USER_LOGIN = "gnoujqtgpyxews";
    public static final String USER_PASSWORD = "c05d60807f1a76c1447fcafb2906941992b1e529dfd0f6c468978e4ce0661ef7";


    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    public static String getUserLogin() {
        return USER_LOGIN;
    }

    public static String getUserPassword() {
        return USER_PASSWORD;
    }
}
