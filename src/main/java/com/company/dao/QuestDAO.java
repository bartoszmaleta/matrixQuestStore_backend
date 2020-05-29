package com.company.dao;

import com.company.models.Quest;

import java.sql.*;
import java.util.ArrayList;

public class QuestDAO {
    ArrayList<Quest> listOfQuests;
    ConnectionFactory conFactory;

    public QuestDAO() {
        conFactory = new ConnectionFactory();

    }

    public void addQuest(Quest quest) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("INSERT INTO \"Quests\" (title, description, coins, image, mentor_id)" +
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
            ps = conFactory.getConnection().prepareStatement("DELETE FROM \"Quests\" WHERE id =" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Quest> readQuestList() {
        listOfQuests = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Quests\";");
            while (rs.next()) {
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

    public void readAllQuestsOrderById() {
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Quests\" ORDER BY id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("coins");
                String imgSrc = rs.getString("image");
                Timestamp mentorId = rs.getTimestamp("mentor_id");

                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s\n";
                System.out.printf(format, id, title, description, price, imgSrc, mentorId);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAllQuestsOrderByAmountOfCoins() {
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Quests\" ORDER BY coins;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("coins");
                String imgSrc = rs.getString("image");
                Timestamp mentorId = rs.getTimestamp("mentor_id");

                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s\n";
                System.out.printf(format, id, title, description, price, imgSrc, mentorId);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewQuests(ArrayList<Quest> listOfQuests) {
        for (Quest quest : listOfQuests) {
            System.out.println(quest);
        }
    }

    public void updateQuestTitleById(int id, String title) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET surname = '" + title + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuestDescriptionById(int id, String description) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET surname = '" + description + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuestCoinsById(int id, int amountOfCoins) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET surname = " + amountOfCoins +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuestIdMentorById(int id, int mentorId) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET surname = " + mentorId +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
