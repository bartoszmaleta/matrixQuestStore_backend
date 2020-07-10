package com.company.dao;

import com.company.model.Award;
import com.company.model.StudentAward;
import com.company.model.user.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentsAwardsDaoDb implements StudentsAwardsDao {

    private List<StudentAward> studentAwardList;
    private ConnectionFactory conFactory;

    public StudentsAwardsDaoDb() {
        this.conFactory = new ConnectionFactory();
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
        StudentAward studentAward = (StudentAward) o;
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("INSERT INTO \"Students_Awards\" (user_id, award_id, price, bought_at)" +
                    "VALUES (?, ?, ?, ?);");
            // TODO: remove id inserting!
            ps.setInt(1, studentAward.getUserId());
            ps.setInt(2, studentAward.getAwardId());
            ps.setInt(3, studentAward.getPrice());
            ps.setTimestamp(4, studentAward.getBoughAt());
            ps.executeUpdate();

            ps.close();
            conFactory.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
