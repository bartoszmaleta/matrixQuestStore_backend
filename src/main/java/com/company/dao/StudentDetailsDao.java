package com.company.dao;

public interface StudentDetailsDao {
    boolean insert(int studentIdFromUsersTable);

    boolean setMentorForUser(int studentId, int mentorId);

    String getStudentsMentorsName(int studentId);

    int getStudentCoins(int id);
}
