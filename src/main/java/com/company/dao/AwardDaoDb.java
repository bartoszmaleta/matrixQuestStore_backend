package com.company.dao;

import com.company.model.Award;
import com.company.model.Quest;
import com.company.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AwardDaoDb implements AwardDao {
    private List<Award> listOfAwards;
    private final ConnectionFactory conFactory;

    public AwardDaoDb(ConnectionFactory conFactory) {
        this.conFactory = conFactory;
    }

    public AwardDaoDb() {
        conFactory = new ConnectionFactory();
    }


    @Override
    public List<Award> getAwardsByUser(User user) {
        List<Award> awards = new ArrayList<>();
        String userId = String.valueOf(user.getId());

        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Transactions\".id\n" +
                    "     , user_id\n" +
                    "     , (CONCAT(u.name, ' ', u.surname)) AS owner\n" +
                    "     , created_at\n" +
                    "     , award_id\n" +
                    "     , aw.title\n" +
                    "     , aw.description\n" +
                    "     , aw.image\n" +
                    "     , aw.creator_id\n" +
                    "     , \"Transactions\".price\n" +
                    "    FROM \"Transactions\"\n" +
                    "\n" +
                    "INNER JOIN users u\n" +
                    "ON \"Transactions\".user_id = u.id\n" +
                    "\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM \"Awards\"\n" +
                    "        ) aw\n" +
                    "ON \"Transactions\".award_id = aw.id\n" +
                    "\n" +
//                    "WHERE user_id = 29\n" +        // TODO: userId????
                    "WHERE user_id = " + userId + "\n" +        // TODO: with userId
                    "\n" +
                    "ORDER BY \"Transactions\".id;");

            while (rs.next()) {
                int id = rs.getInt("award_id");
                String owner = rs.getString("owner");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
                int creatorId = rs.getInt("creator_id");
                Timestamp dataCreation = rs.getTimestamp("created_at");
//                String mentorDetails = rs.getString("mentor");

                Award award = new Award(id
                        , owner
                        , title
                        , description
                        , price
                        , imageSrc
                        , creatorId
                        , dataCreation);
                awards.add(award);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return awards;
    }

    @Override
    public List<Award> readAwardListByMentor(User user) {
        listOfAwards = new ArrayList<>();
        String userIdStr = String.valueOf(user.getId());
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Awards\".creator_id = m.id\n" +
                    "WHERE \"Quests\".mentor_id = " +
                    userIdStr +
                    "ORDER BY \"Awards\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
//                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, mentorDetails));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAwards;
    }

    @Override // TODO: Use this in server MentorHandler!!
    public List<Award> readAwardListByMentorById(int userId) {
        listOfAwards = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Awards\".creator_id = m.id\n" +
                    "WHERE \"Awards\".creator_id = " +
                    userId +
                    "ORDER BY \"Awards\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, mentorDetails));
            }
            rs.close();
            conFactory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAwards;
    }

    @Override
    public List<Award> readAwardListWithMentors() {
        return null;
    }

    @Override
    public void deleteAwardById(int id) {
        PreparedStatement ps = null;

        try {
            ps = conFactory.getConnection().prepareStatement("DELETE FROM \"Awards\" WHERE id =" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAward(Award award) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("INSERT INTO \"Awards\" (id, title, description, price, image, data_creation, creator_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            // TODO: remove id inserting!
            ps.setInt(1, award.getId());
            ps.setString(2, award.getTitle());
            ps.setString(3, award.getDescription());
            ps.setInt(4, award.getPrice());
            ps.setString(5, award.getImageSrc());
            ps.setTimestamp(6, award.getDataCreation());
            ps.setInt(7, award.getMentorId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAwardTitleById(int id, String title) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET title = '" + title + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateAwardDescriptionById(int id, String description) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET description = '" + description + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAwardPriceById(int id, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be below 0");
        }
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET price = " + price +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAwardCreatorIdById(int id, int creatorId) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = " + creatorId +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Award> getAllElements() {
        listOfAwards = new ArrayList<>();
//        String userIdStr = String.valueOf(user.getId());
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Awards\".creator_id = m.id\n" +
//                    "WHERE \"Quests\".mentor_id = " +
//                    userIdStr +
                    "ORDER BY \"Awards\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
//                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, mentorDetails));
            }
            conFactory.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAwards;
    }

    @Override
    public Award getById(int id) {
        listOfAwards = new ArrayList<>();
        Award award = null;
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, (CONCAT(c.name, ' ', c.surname)) AS creator FROM \"Awards\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) c\n" +
                    "ON \"Awards\".creator_id = c.id\n" +
                    "WHERE \"Awards\".id = " +
                    id +
                    "ORDER BY \"Awards\".id;");
            while (rs.next()) {
                int idAward = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
                String creator = rs.getString("creator");

                award = new Award(idAward, title, description, price, imageSrc, creator);
                listOfAwards.add(award);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return listOfQuests;
        return award;
    }

    @Override
    public boolean insert(Award award) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("INSERT INTO \"Awards\" (title, description, price, image, data_creation, creator_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?);");
            // TODO: remove id inserting!
            ps.setString(1, award.getTitle());
            ps.setString(2, award.getDescription());
            ps.setInt(3, award.getPrice());
            ps.setString(4, award.getImageSrc());
            ps.setTimestamp(5, award.getDataCreation());
            ps.setInt(6, award.getMentorId());
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
    public boolean edit(Award award) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement ps = null;

        try {
            ps = conFactory.getConnection().prepareStatement("DELETE FROM \"Awards\" WHERE id =" + id + ";");
            ps.executeUpdate();
            conFactory.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

