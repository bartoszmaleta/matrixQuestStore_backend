package com.company.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDetailsDaoDb implements StudentDetailsDao {
    ConnectionFactory connectionFactory;

    public StudentDetailsDaoDb() {
        this.connectionFactory = new ConnectionFactory();
    }

    public StudentDetailsDaoDb(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public boolean insert(int studentIdFromDao) {
        PreparedStatement ps = null;

        try {
            ps = connectionFactory
                    .getConnection()
                    .prepareStatement(
                            "INSERT INTO \"Student_Detailss\" (student_id) " + // TODO: drop Student_Detail
                                    "VALUES ('" + studentIdFromDao + "');"); // // TODO: Rename without "s"

            ps.executeUpdate();
            ps.close();
            connectionFactory.close();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public List getAllElements() {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean edit(Object o) {
        return false;
    }

    @Override
    public boolean delete (int studentId) {
        PreparedStatement ps = null;

        try {
            ps = connectionFactory
                    .getConnection()
                    .prepareStatement(
                            "DELETE FROM \"Student_Detailss\" WHERE " + //
                                    "student_id = '" + studentId + "');"); // // TODO: Rename without "s"

            ps.executeUpdate();
            ps.close();
            connectionFactory.close();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public int getStudentCoins(int id) {
        int coins = 0;
        try {
            ResultSet rs = connectionFactory.executeQuery("SELECT\n" +
                    "    sd.coins AS coins\n" +
                    "FROM users u\n" +
                    "LEFT JOIN \"Student_Detailss\" sd\n" +
                    "ON u.id = sd.student_id\n" +
                    "WHERE u.id = " + id + ";");

            rs.next();
            coins = rs.getInt("coins");

            rs.close();
            connectionFactory.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return coins;
    }

    @Override
    public boolean subtractAwardPriceFromUserById(int studentId, int priceOfAward) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE \"Student_Detailss\"\n" +
                    "    SET coins = coins - " + priceOfAward + "\n" +
                    "WHERE\n" +
                    "    student_id = " + studentId + ";");
            ps.executeUpdate();
            connectionFactory.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getStudentsMentorsName(int studentId) {
        String mentorsName = "";
        try {
            ResultSet rs = connectionFactory.executeQuery("SELECT\n" +
                    "    CONCAT(u.name, ' ', u.surname) AS mentor\n" +
                    "FROM users u\n" +
                    "WHERE u.id = (\n" +
                    "\n" +
                    "SELECT\n" +
                    "    sd.mentor_id AS mentor\n" +
                    "FROM \"Student_Detailss\" sd\n" +
                    "WHERE student_id = " + studentId + ");");
            // TODO: defense from injecting!

            rs.next();
            mentorsName = rs.getString("mentor");

            rs.close();
            connectionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mentorsName;
    }

    @Override
    public boolean setMentorForUser(int studentId, int mentorId) {
//        TODO:
        return false;
    }
}
