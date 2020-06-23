package com.company.dao;

import com.company.models.users.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDetailsDaoDb implements StudentDetailsDao {
    ConnectionFactory connectionFactory;

    public StudentDetailsDaoDb() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public boolean insert(int studentIdFromDao) {
//        User user = (User) o;
        PreparedStatement ps = null;

//        int studentId = user.getId();
        try {
            ps = connectionFactory.getConnection().prepareStatement("INSERT INTO \"Student_Details\" (student_id) " +
                    "VALUES (?);");
            ps.setInt(1, studentIdFromDao);
            ps.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
