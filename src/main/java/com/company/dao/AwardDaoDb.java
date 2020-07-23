package com.company.dao;

import com.company.model.Award;
import com.company.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AwardDaoDb implements AwardDao {
    private List<Award> listOfAwards;
    private final ConnectionFactory conFactory;

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
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, mentorDetails));
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
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, mentorDetails));
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
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = '" + title + "' " +
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
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = '" + description + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAwardPriceById(int id, int price) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = " + price +
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

    public void cleanUpDatabase() {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("ALTER TABLE \"Awards\"\n" +
                    "DROP CONSTRAINT \"Awards_pkey\";\n" +
                    "\n" +
                    "ALTER TABLE \"Awards\"\n" +
                    "DROP CONSTRAINT \"Awards_creator_id_fkey\";" +
                    "DROP TABLE IF EXISTS \"Awards\";" +
                    "\ncreate table \"Awards\"\n" +
                    "(\n" +
                    "    id            serial  not null\n" +
                    "        constraint \"Awards_pkey\"\n" +
                    "            primary key,\n" +
                    "    title         text,\n" +
                    "    description   text,\n" +
                    "    price         integer,\n" +
                    "    image         text,\n" +
                    "    data_creation timestamp,\n" +
                    "    creator_id    integer not null\n" +
                    "        constraint \"Awards_creator_id_fkey\"\n" +
                    "            references users\n" +
                    "            on update cascade on delete cascade\n" +
                    ");\n" +
                    "\n" +
                    "alter table \"Awards\"\n" +
                    "    owner to gnoujqtgpyxews;\n" +
                    "\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Martial-Art Workshop', '1h of learning java-jitsu with mentor', 60, 'morpheusCome.png', '2020-04-28 11:04:49.000000', 1);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Plugged in', '1h of studying in simulator with friend', 50, 'simulator.jpg', '2020-04-26 11:06:37.000000', 5);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('I was blind now I see', '1h workshop of binary numbers as real lfe images', 50, 'blindsee.jpg', '2020-04-27 11:04:47.000000', 1);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Consult The Oracle', '45 minutes of interwiev with oracle from school office', 40, 'oracle.jpg', '2020-04-27 11:06:46.000000', 2);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Holy Trinity Help', 'Get help with your project for 1h from three mentors at the same time', 100, 'holy3.jpg', '2020-04-26 11:06:53.000000', 22);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Mr Robot', '1h of programming the bot of your favourite mentor', 70, '01.png', '2020-04-27 11:06:32.000000', 22);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Nokia Software', '1h programming course on oldstyle Nokia', 20, 'nokianeo.png', '2020-04-27 10:54:49.000000', 5);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Eyewear Of Matrix', 'All mentors wear matrix sunglasses whole day test', 120, 'sunglasses.jpg', '2020-04-28 11:07:05.000000', 22);\n" +
                    "INSERT INTO public.\"Awards\" (title, description, price, image, data_creation, creator_id) VALUES ('Hackagov', '30 min of hacking Polish National Bank''s website', 30, 'hacking.jpg', '2020-07-08 20:44:34.195000', 2);");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, mentorDetails));
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
        return null;
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

