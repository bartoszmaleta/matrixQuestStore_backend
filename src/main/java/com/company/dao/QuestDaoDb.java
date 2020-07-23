package com.company.dao;

import com.company.model.Quest;
import com.company.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestDaoDb implements QuestDao {
    List<Quest> listOfQuests;
    ConnectionFactory conFactory;

    public QuestDaoDb() {
        conFactory= new ConnectionFactory();
    }

    public QuestDaoDb(ConnectionFactory conFactory) {
        this.conFactory= conFactory;
    }

    @Override
    public void updateQuestTitleById(int id, String title) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET title = '" + title + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestDescriptionById(int id, String description) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET description = '" + description + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestCoinsById(int id, int amountOfCoins) {
        if(amountOfCoins < 0) {
            throw new IllegalArgumentException("Amount of coins can't be below 0");
        }

        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET coins = " + amountOfCoins +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestIdMentorById(int id, int mentorId) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Quests\" SET mentor_id = 5 WHERE id = 2 ;");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------
    // Read by mentors quests

    public void cleanUpDatabase(){
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("ALTER TABLE \"Quests\"\n" +
                    "DROP CONSTRAINT \"Quests_pkey\";\n" +
                    "\n" +
                    "ALTER TABLE \"Quests\"\n" +
                    "DROP CONSTRAINT \"Quests_mentor_id_fkey\";" +
                    "DROP TABLE IF EXISTS \"Quests\";" +
                    "\ncreate table \"Quests\"\n" +
                    "(\n" +
                    "    id          serial not null\n" +
                    "        constraint \"Quests_pkey\"\n" +
                    "            primary key,\n" +
                    "    title       text,\n" +
                    "    description text,\n" +
                    "    coins       text,\n" +
                    "    image       text,\n" +
                    "    mentor_id   integer\n" +
                    "        constraint \"Quests_mentor_id_fkey\"\n" +
                    "            references users\n" +
                    ");\n" +
                    "\n" +
                    "alter table \"Quests\"\n" +
                    "    owner to gnoujqtgpyxews;\n" +
                    "\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Dodge This!', 'Dodge 5x times morning question', '5', 'dodgeThis.jpg', 2);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Faster than ever', 'Submit project one day earlier', '50', 'neoFly.png', 1);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Best Trio', 'Stand on the podium in Kahoot', '12', 'podium.jpg', 2);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Be everywhere', 'Attendence more than 80%', '9', 'appearences.png', 2);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Telephone Booth Call', '3x times teleporting from telephone booth to headquarter', '30', 'telephoneBooth.png', 5);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Avoid bullets', 'Answer mentor''s question correctly', '666', 'dodgeBullets.jpg', 1);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Help friend in need', 'Review your friend''s code for test', '6', 'helpFriend.jpg', 2);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Destroy bugs for test', 'Find and fix 3 bugs in someone else''s program', '7', 'bugs.jpg', 2);\n" +
                    "INSERT INTO public.\"Quests\" (title, description, coins, image, mentor_id) VALUES ('Defend humanity', 'Succesfully pass a checkpoint', '15', 'defendAgainstSmith.jpg', 2);");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Quest> readQuestListByMentor(User user) {
        listOfQuests = new ArrayList<>();
        String userIdStr = String.valueOf(user.getId());
        try {
//            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Quests\" ORDER BY id;");
            ResultSet rs = conFactory.executeQuery("SELECT \"Quests\".id, title, description, coins, image, mentor_id, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Quests\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Quests\".mentor_id = m.id\n" +
                    "WHERE \"Quests\".mentor_id = " +
                    user.getId() +
                    "ORDER BY \"Quests\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("coins");
                String imageSrc = rs.getString("image");
                String mentor = rs.getString("mentor");
                int mentorId = rs.getInt("mentor_id");

                listOfQuests.add(new Quest(id, title, description, price, imageSrc, mentor, mentorId));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfQuests;
    }

    @Override
    public List<Quest> readQuestListByMentorById(int userIdStr) {
        listOfQuests = new ArrayList<>();
//        String userIdStr = String.valueOf(user.getId());
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Quests\".id, title, description, coins, image, mentor_id, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Quests\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Quests\".mentor_id = m.id\n" +
                    "WHERE \"Quests\".mentor_id = " +
                    userIdStr +
                    "ORDER BY \"Quests\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("coins");
                String imageSrc = rs.getString("image");
                String mentor = rs.getString("mentor");
                int mentorId = rs.getInt("mentor_id");

                listOfQuests.add(new Quest(id, title, description, price, imageSrc, mentor, mentorId));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfQuests;
    }

    // Implemented from interface DAO
    @Override
    public List<Quest> getAllElements() {
        listOfQuests = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Quests\".id, title, description, coins, image, mentor_id, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Quests\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Quests\".mentor_id = m.id\n" +
                    "ORDER BY \"Quests\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("coins");
                String imageSrc = rs.getString("image");
                String mentor = rs.getString("mentor");
                int mentorId = rs.getInt("mentor_id");

                listOfQuests.add(new Quest(id, title, description, price, imageSrc, mentor, mentorId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfQuests;
    }

    @Override
    public Quest getById(int id) {
        listOfQuests = new ArrayList<>();
        Quest quest = null;
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Quests\".id, title, description, coins, image, mentor_id, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Quests\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Quests\".mentor_id = m.id\n" +
                    "WHERE \"Quests\".id = " +
                    id +
                    "ORDER BY \"Quests\".id;");
            while (rs.next()) {
                int idQuest = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("coins");
                String imageSrc = rs.getString("image");
                String mentor = rs.getString("mentor");
                int mentorId = rs.getInt("mentor_id");

                quest = new Quest(idQuest, title, description, price, imageSrc, mentor, mentorId);
                listOfQuests.add(quest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quest;
    }

    @Override
    public boolean insert(Quest quest) {
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
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(Quest quest) {
        return false;
    }

    @Override
    public boolean delete(int id) {     // Delete quest by id
        PreparedStatement ps = null;

        try {
            ps = conFactory.getConnection().prepareStatement("DELETE FROM \"Quests\" WHERE id =" + id + ";");
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
