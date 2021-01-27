package com.company.dao;

public interface StudentDetailsDao extends Dao {
    boolean insert(int studentIdFromUsersTable);

    boolean setMentorForUser(int studentId, int mentorId);

    String getStudentsMentorsName(int studentId);

    int getStudentCoins(int id);

    boolean subtractAwardPriceFromUserById(int studentId, int priceOfAward);
}
