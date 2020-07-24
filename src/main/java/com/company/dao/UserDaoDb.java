package com.company.dao;

import com.company.model.user.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDb implements UserDao {
    ConnectionFactory connectionFactory;
    StudentDetailsDao studentDetailsDao;

    // Without dependency injection
    public UserDaoDb() {
        this.connectionFactory = new ConnectionFactory();
        this.studentDetailsDao = new StudentDetailsDaoDb();
    }

    // With dependency injection
    public UserDaoDb(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        this.studentDetailsDao = new StudentDetailsDaoDb(connectionFactory);
    }

    public User readUserByEmailAndPassword(String userEmail, String userPassword) {
        User newUser;

        try {
            ResultSet rs;
            rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"email\" = '" + userEmail + "' AND \"password\" = '" + userPassword + "';");
            newUser = getUser(rs);
            connectionFactory.close();
            rs.close();
            if (newUser != null) {
                return newUser;
            }
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
            return null;
        }
        return null;
    }

    private User getUser(ResultSet rs) throws SQLException {
        rs.next();
        if (rs.getInt("role_id") == 1) {
            return getAdmin(rs);
        } else if (rs.getInt("role_id") == 2) {
            return getMentor(rs);
        } else if (rs.getInt("role_id") == 3) {
            return getStudent(rs);
        } else {
            System.out.println("No user");
        }
        return null;
    }

    private User getStudent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String email = rs.getString("email");
        int roleId = rs.getInt("role_id");
        String avatarPath = rs.getString("avatar");

        String mentorName = this.studentDetailsDao.getStudentsMentorsName(id);
        int coins = this.studentDetailsDao.getStudentCoins(id);
        String module = getStudentModule(id);

        User newUser = new Student(id, name, surname, login, password, email, roleId, avatarPath, coins, module, mentorName);

        connectionFactory.close();
        rs.close();
        return newUser;
    }

    private String getStudentModule(int id) {
        String module = "";
        try {
            ResultSet rs = connectionFactory.executeQuery("SELECT\n" +
                    "    m.name AS module\n" +
                    "FROM users u\n" +
                    "LEFT JOIN \"Students_Modules\" sm\n" +
                    "ON sm.student_id = u.id\n" +
                    "LEFT JOIN \"Modules\" m\n" +
                    "ON m.id = sm.module_id\n" +
                    "WHERE u.id = " + id + ";");

            while (rs.next()) {
                module = rs.getString("module");
            }
            rs.close();
            connectionFactory.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return module;
    }

    private User getMentor(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String email = rs.getString("email");
        int roleId = rs.getInt("role_id");
        String avatarPath = rs.getString("avatar");

        User newUser = new Mentor(id, name, surname, login, password, email, roleId, avatarPath);

        connectionFactory.close();
        rs.close();
        return newUser;
    }

    private User getAdmin(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String email = rs.getString("email");
        int roleId = rs.getInt("role_id");
        String avatarPath = rs.getString("avatar");

        User newUser = new Admin(id, name, surname, login, password, email, roleId, avatarPath);

        connectionFactory.close();
        rs.close();
        return newUser;
    }

    private int decideRole(Role userRole) {
        return switch (userRole) {
            case ADMIN -> 1;
            case MENTOR -> 2;
            case STUDENT -> 3;
        };
    }

    public List<User> getStudents() {
        List<User> students = new ArrayList<>();
        try {
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users WHERE role_id = 3 ORDER BY id;");
            User newUser;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int roleId = rs.getInt("role_id");
                String avatarPath = rs.getString("avatar");

                // TODO: 3 queries!!!!!!!!
                String mentorName = this.studentDetailsDao.getStudentsMentorsName(id);
                System.out.println("mentorName = " + mentorName);
                int coins = this.studentDetailsDao.getStudentCoins(id);
                String module = getStudentModule(id);

                newUser = new Student(id, name, surname, login, password, email, roleId, avatarPath, coins, module, mentorName);

                // TODO: check this! should be okay!
//              student = getStudent(rs); TODO: change to this, because it is enough!!!

                students.add(newUser);
            }
            rs.close();
            connectionFactory.close();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getMentors() {
        List<User> mentors = new ArrayList<>();
        try {
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users WHERE role_id = 2 ORDER BY id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int roleId = rs.getInt("role_id");
                String avatarPath = rs.getString("avatar");

                User newUser = new Mentor(id, name, surname, login, password, email, roleId, avatarPath);

                mentors.add(newUser);
            }
            rs.close();
            connectionFactory.close();
            return mentors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ---------------------------------------
    // EDIT

    // TODO or not???
    @Override
    public boolean edit(User o) {
        return false;
    }

    public void updateUserNameById(int id, String name) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE users SET name = '" + name + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserSurnameById(int id, String surname) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE users SET surname = '" + surname + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserLoginById(int id, String login) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE users SET login = '" + login + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserPasswordById(int id, String password) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE users SET password = '" + password + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUserEmailById(int id, String email) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE users SET email = '" + email + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: use it in edit menu!
    public void updateStudentAvatarPathById(int id, String avatarPath) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE users SET avatar = '" + avatarPath + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    END OF EDIT SECTION ---------------------------------------

    @Override
    public int readUserIdByEmail(String email) {
        PreparedStatement ps = null;
        if (email.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        try {
            ps = connectionFactory.getConnection().prepareStatement("SELECT * FROM \"users\" WHERE \"email\" = '" + email + "';");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            rs.close();
            connectionFactory.close();
            return id;
        } catch (Exception e) {
            System.err.println("Error! Reading user by id from DB failed!");
            return Integer.MIN_VALUE;
        }
    }


    // ---------------------------------------

    @Override
    public List getAllElements() {
        List<User> users = new ArrayList<>();
        try {
            User newUser;
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users ORDER BY id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int roleId = rs.getInt("role_id");
                String avatarSource = rs.getString("avatar");

                if (roleId == 1) {
                    newUser = new Admin(id, name, surname, login, password, email, roleId, avatarSource);
                    users.add(newUser);
                } else if (roleId == 2) {
                    newUser = new Mentor(id, name, surname, login, password, email, roleId, avatarSource);
                    users.add(newUser);
                } else if (roleId == 3) {
                    newUser = new Student(id, name, surname, login, password, email, roleId, avatarSource);
                    users.add(newUser);
                }
            }
            connectionFactory.close();
            rs.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(int id) {
        if (id < 1) return null;
        User newUser;

        try {
            ResultSet rs;

            rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"id\" = '" + id + "';");

            newUser = getUser(rs);
            connectionFactory.close();
            rs.close();
            return newUser;
        } catch (Exception e) {
            System.err.println("Error! Reading user by id from DB failed!");
        }
        return null;
    }


    @Override
    public boolean insert(User user) {
        PreparedStatement ps = null;
        Role userRole = user.getRole();

        int roleId = decideRole(userRole);

        try {
            ps = connectionFactory.getConnection().prepareStatement("INSERT INTO users (name, surname, login, password, email, role_id, avatar)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setInt(6, roleId);
//            ps.setInt(7, user.getUserDetailId());
            ps.setString(7, user.getAvatarSource());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        if (id < 1) return false;
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM users WHERE id = '" + id + "';");
            boolean foundUserToDelete = ps.executeUpdate() != 0;
            ps.close();
            connectionFactory.close();
            return foundUserToDelete;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteStudentDetails(int id) {
        if (id < 1) return false;
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM \"Student_Detailss\" WHERE student_id = '" + id + "';");
            boolean foundUserToDelete = ps.executeUpdate() != 0;
            ps.close();
            connectionFactory.close();
            return foundUserToDelete;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    // TODO: should change location to StudentsModulesDaoDb
    private boolean deleteStudentModules(int id) {
        if (id < 1) return false;
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM \"Students_Modules\" WHERE student_id = '" + id + "';");
            boolean foundUserToDelete = ps.executeUpdate() != 0;
            ps.close();
            connectionFactory.close();
            return foundUserToDelete;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        if (id < 1) return false;

        PreparedStatement ps = null;
        try {
            deleteStudentModules(id);
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM users WHERE id = '" + id + "' AND " + "role_id = 3" + ";");
            boolean foundUserToDelete = ps.executeUpdate() != 0;
            deleteStudentDetails(id);
            ps.close();
            connectionFactory.close();
            return foundUserToDelete;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void dropTables() {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement
                    ("DROP TABLE \"Awards\";\n" +
                            "DROP TABLE \"Quests\";\n" +
                            "DROP TABLE \"Students_Awards\";\n" +
                            "DROP TABLE \"Roles\";\n" +
                            "DROP TABLE \"Transactions\";\n" +
                            "DROP TABLE \"Student_Detailss\";\n" +
                            "DROP TABLE \"Students_Modules\";\n" +
                            "DROP TABLE \"Modules\";\n" +
                            "DROP TABLE \"users\";");
            ps.executeUpdate();
            ps.close();
            connectionFactory.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTables() {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement
                    ("create table \"Roles\"\n" +
                            "(\n" +
                            "    id   serial not null,\n" +
                            "    role text\n" +
                            ");\n" +
                            "\n" +
                            "alter table \"Roles\"\n" +
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
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
                            "    owner to pirqathgcgzhbg;\n" +
                            "\n" +
                            "\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Nokia Software', '1h programming course on oldstyle Nokia', 20, 'nokianeo.png', '2020-04-27 10:54:49.000000', 19);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('I was blind now I see', '1h workshop of binary numbers as real lfe images', 50, 'blindsee.jpg', '2020-04-27 11:04:47.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Martial-Art Workshop', '1h of learning java-jitsu with mentor for test', 60, 'morpheusCome.png', '2020-04-28 11:04:49.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Mr Robot', '1h of programming the bot of your favourite mentor', 70, '01.png', '2020-04-27 11:06:32.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Plugged in for test', '1h of studying in simulator with friend', 50, 'simulator.jpg', '2020-04-26 11:06:37.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Consult The Oracle', '45 minutes of interwiev with oracle from school office', 40, 'oracle.jpg', '2020-04-27 11:06:46.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Holy Trinity Help', 'Get help with your project for 1h from three mentors at the same time', 100, 'holy3.jpg', '2020-04-26 11:06:53.000000', 20);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Eyewear Of Matrix', 'All mentors wear matrix sunglasses whole day test', 120, 'sunglasses.jpg', '2020-04-28 11:07:05.000000', 19);\n" +
                            "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ( 'Hackagov', '30 min of hacking Polish National Bank''s website', 30, 'hacking.jpg', '2020-07-08 20:44:34.195000', 19);\n" +
                            "\n");
            ps.executeUpdate();
            ps.close();
            connectionFactory.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}