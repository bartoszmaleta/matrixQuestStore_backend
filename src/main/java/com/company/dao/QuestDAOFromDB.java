package com.company.dao;

import com.company.DBConnector;
import com.company.models.Quest;

import java.sql.*;
import java.util.ArrayList;

public class QuestDAOFromDB {
    Connection c;
    Statement st;
    ArrayList<Quest> listOfQuests;

    public QuestDAOFromDB(DBConnector dbConnector) {
        this.c = dbConnector.getConnection();
        this.st = dbConnector.getSt();
    }

    public void addQuest(Quest quest) {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("INSERT INTO \"Quests\" (title, description, coins, image, mentor_id)" +
                    "VALUES (?, ?, ?, ?, ?);");
            ps.setString(1, quest.getTitle());
            ps.setString(2, quest.getDescription());
            ps.setInt(3, quest.getPrice());
            ps.setString(4, quest.getImageSrc());
            ps.setInt(5, quest.getMentorId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuest(Quest quest) {

    }

    public void deleteQuestById(int id) {
        PreparedStatement ps = null;

        try {
            ps = this.c.prepareStatement("DELETE FROM \"Quests\" WHERE id =" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Quest> readQuestList() {
        listOfQuests = new ArrayList<>();
        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM \"Quests\";");
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("coins");
                String imageSrc = rs.getString("image");
                int mentorId = rs.getInt("mentor_id");

                listOfQuests.add(new Quest(id, title, description, price, imageSrc, mentorId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfQuests;
    }

    public void viewQuests(ArrayList<Quest> listOfQuests) {
        for(Quest quest: listOfQuests) {
            System.out.println(quest);
        }
    }
}
