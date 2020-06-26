package com.company.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDetailsDaoDb implements StudentDetailsDao {
    ConnectionFactory connectionFactory;

    public StudentDetailsDaoDb() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public boolean insert(int studentIdFromDao) {
        PreparedStatement ps = null;

        try {
            System.out.println("student id form db = " + studentIdFromDao);

            ps = connectionFactory
                    .getConnection()
                    .prepareStatement(
                            "INSERT INTO \"Student_Detailss\" (student_id) " + // TODO: drop Student_Detail
                    "VALUES ('" + studentIdFromDao + "');"); // // TODO: Rename without "s"

            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
