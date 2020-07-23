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

    public AwardDaoDb(ConnectionFactory conFactory) {
        this.conFactory = conFactory;
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
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, creator_id, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
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
                int creatorId = rs.getInt("creator_id");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, creatorId));
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
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, creator_id, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
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
                int creatorId = rs.getInt("creator_id");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, creatorId));
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
        if(price < 0) {
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
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET creatorId = " + creatorId +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement
                    ("create table \"Roles\"\n" +
                            "(\n" +
                            "    id   serial not null,\n" +
                            "    role text\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Roles\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "insert into public.\"Roles\" (role) values ('admin');\n" +
                            "insert into public.\"Roles\" (role) values ('mentor');\n" +
                            "insert into public.\"Roles\" (role) values ('student');\n" +
                            "\n" +
                            "create table \"users\"\n" +
                            "(\n" +
                            "    id       serial not null\n" +
                            "        constraint users_pk\n" +
                            "            primary key,\n" +
                            "    name     varchar,\n" +
                            "    surname  varchar,\n" +
                            "    login    varchar,\n" +
                            "    password varchar,\n" +
                            "    email    varchar,\n" +
                            "    role_id  integer,\n" +
                            "    avatar   varchar\n" +
                            ");\n" +
                            "\n" +
                            "alter table users\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "create unique index users_id_uindex\n" +
                            "    on users (id);\n" +
                            "\n" +
                            "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Cristiano', 'Ronaldo', 'cr7', 'pass', 'cristiano@gmail', 3, '../resoruce/avatars/cristiano@gmail.com_logo');\n" +
                            "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Nikola', 'Tesla', 'tesla', 'pass', 'tesla@gmail', 2, '../resoruce/avatars/tesla@gmail.com_logo');\n" +
                            "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Bill', 'Gates', 'microsoft', 'pass', 'microsoft@gmail', 1, '../resoruce/avatars/microsoft@gmail.com_logo');\n" +
                            "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Warren', 'Buffet', 'berkshire', 'pass', 'berkshire@gmail', 2, '../resoruce/avatars/berkshire@gmail.com_logo');\n" +
                            "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Jeff', 'Bezos', 'amazon', 'pass', 'amazon@gmail', 3, '../resoruce/avatars/amazon@gmail.com_logo');\n" +
                            "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Linus', 'Torvalds', 'linux', 'pass', 'linux@gmail', 1, '../resoruce/avatars/linux@gmail.com_logo_logo');\n" +
                            "\n" +
                            "\n" +
                            "create table \"Student_Detailss\"\n" +
                            "(\n" +
                            "    id         serial not null\n" +
                            "        constraint student_detailss_pk\n" +
                            "            primary key,\n" +
                            "    coins      integer default 0,\n" +
                            "    student_id integer,\n" +
                            "    mentor_id  integer default 2\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Student_Detailss\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "create unique index student_detailss_id_uindex\n" +
                            "    on \"Student_Detailss\" (id);\n" +
                            "\n" +
                            "\n" +
                            "insert into public.\"Student_Detailss\" (coins, student_id, mentor_id) values (220, 1, 2);\n" +
                            "insert into public.\"Student_Detailss\" (coins, student_id, mentor_id) values (600, 5, 2);\n" +
                            "\n" +
                            "create table \"Modules\"\n" +
                            "(\n" +
                            "    id   serial not null\n" +
                            "        constraint modules_pk\n" +
                            "            primary key,\n" +
                            "    name text\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Modules\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "insert into public.\"Modules\" (name) values ('JAVA');\n" +
                            "insert into public.\"Modules\" (name) values ('PYTHON');\n" +
                            "insert into public.\"Modules\" (name) values ('WEB');\n" +
                            "insert into public.\"Modules\" (name) values ('ADVANCE');\n" +
                            "\n" +
                            "create table \"Students_Modules\"\n" +
                            "(\n" +
                            "    id            serial not null\n" +
                            "        constraint \"User_Modules_pkey\"\n" +
                            "            primary key,\n" +
                            "    student_id    integer\n" +
                            "        constraint \"User_Modules_user_id_fkey\"\n" +
                            "            references users,\n" +
                            "    module_id     integer\n" +
                            "        constraint \"User_Modules_module_id_fkey\"\n" +
                            "            references \"Modules\",\n" +
                            "    enrollment_at timestamp\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Students_Modules\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "insert into public.\"Students_Modules\" (student_id, module_id, enrollment_at) values (1, 1, '2020-07-23 18:13:48.000000');\n" +
                            "insert into public.\"Students_Modules\" (student_id, module_id, enrollment_at) values (5, 2, '2020-07-23 18:14:02.000000');\n" +
                            "\n" +
                            "\n" +
                            "create table \"Transactions\"\n" +
                            "(\n" +
                            "    id         serial not null,\n" +
                            "    user_id    integer,\n" +
                            "    award_id   integer,\n" +
                            "    price      integer,\n" +
                            "    created_at text\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Transactions\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "insert into public.\"Transactions\" (user_id, award_id, price, created_at) values (1, 2, 20, '2020-07-10 09:00:17.861+02');\n" +
                            "insert into public.\"Transactions\" (user_id, award_id, price, created_at) values (1, 3, 44, '2020-07-10 09:18:11.102+02');\n" +
                            "\n" +
                            "\n" +
                            "create table \"Students_Awards\"\n" +
                            "(\n" +
                            "    id        serial  not null\n" +
                            "        constraint users_awards_pk\n" +
                            "            primary key,\n" +
                            "    user_id   integer not null,\n" +
                            "    award_id  integer not null,\n" +
                            "    price     integer not null,\n" +
                            "    bought_at text    not null\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Students_Awards\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "create unique index users_awards_id_uindex\n" +
                            "    on \"Students_Awards\" (id);\n" +
                            "\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 8, 70, '2020-07-10 07:52:48.119+02');\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (5, 7, 120, '2020-07-10 07:53:31.857+02');\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 6, 70, '2020-07-10 08:04:03.917+02');\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 5, 40, '2020-07-10 09:00:17.861+02');\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 4, 50, '2020-07-10 09:17:54.5+02');\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 3, 30, '2020-07-10 09:18:11.102+02');\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 1, 30, '2020-07-10 10:05:28.217+02');\n" +
                            "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (5, 2, 120, '2020-07-10 10:48:01.526+02');\n" +
                            "\n" +
                            "\n" +
                            "create table \"Quests\"\n" +
                            "(\n" +
                            "    id          serial not null,\n" +
                            "    title       text,\n" +
                            "    description text,\n" +
                            "    coins       text,\n" +
                            "    image       text,\n" +
                            "    mentor_id   integer\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Quests\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Dodge This!', 'Dodge 5x times morning question', 5, 'dodgeThis.jpg', 1);\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Telephone Booth Call', '3x times teleporting from telephone booth to headquarter', 30, 'telephoneBooth.png', 5);\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Destroy bugs for test', 'Find and fix 3 bugs in someone else''s program', 7, 'bugs.jpg', 20);\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Help friend in need', 'Review your friend''s code for test', 6, 'helpFriend.jpg', 20);\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Avoid bullets', 'Answer mentor''s question correctly', 666, 'dodgeBullets.jpg', 20);\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Be everywhere', 'Attendence more than 80%', 9, 'appearences.png', 19);\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Faster than ever', 'Submit project one day earlier', 50, 'neoFly.png', 20);\n" +
                            "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Best Trio', 'Stand on the podium in Kahoot', 12, 'podium.jpg', 19);\n" +
                            "\n" +
                            "\n" +
                            "create table \"Awards\"\n" +
                            "(\n" +
                            "    id            serial not null,\n" +
                            "    title         text,\n" +
                            "    description   text,\n" +
                            "    price         integer,\n" +
                            "    image         text,\n" +
                            "    data_creation timestamp,\n" +
                            "    creator_id    integer\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Awards\"\n" +
                            "    owner to gnoujqtgpyxews;\n" +
                            "\n" +
                            "\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Nokia Software', '1h programming course on oldstyle Nokia', 20, 'nokianeo.png', '2020-04-27 10:54:49.000000', 19);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('I was blind now I see', '1h workshop of binary numbers as real lfe images', 50, 'blindsee.jpg', '2020-04-27 11:04:47.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Martial-Art Workshop', '1h of learning java-jitsu with mentor', 60, 'morpheusCome.png', '2020-04-28 11:04:49.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Mr Robot', '1h of programming the bot of your favourite mentor', 70, '01.png', '2020-04-27 11:06:32.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Plugged in', '1h of studying in simulator with friend', 50, 'simulator.jpg', '2020-04-26 11:06:37.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Consult The Oracle', '45 minutes of interwiev with oracle from school office', 40, 'oracle.jpg', '2020-04-27 11:06:46.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Holy Trinity Help', 'Get help with your project for 1h from three mentors at the same time', 100, 'holy3.jpg', '2020-04-26 11:06:53.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Eyewear Of Matrix', 'All mentors wear matrix sunglasses whole day', 120, 'sunglasses.jpg', '2020-04-28 11:07:05.000000', 19);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ( 'Hackagov', '30 min of hacking Polish National Bank''s website', 30, 'hacking.jpg', '2020-07-08 20:44:34.195000', 19);\n" +
                            "\n");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropTables() {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement(
                    "DROP TABLE if exists \"Awards\" cascade;\n" +
                            "DROP TABLE if exists \"Quests\" cascade;\n" +
                            "DROP TABLE if exists \"Students_Awards\" cascade;\n" +
                            "DROP TABLE if exists \"Roles\" cascade;\n" +
                            "DROP TABLE if exists \"Transactions\" cascade;\n" +
                            "DROP TABLE if exists \"Student_Detailss\" cascade;\n" +
                            "DROP TABLE if exists \"Students_Modules\" cascade;\n" +
                            "DROP TABLE if exists \"Modules\" cascade;\n" +
                            "DROP TABLE if exists \"users\" cascade;"
            );
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

