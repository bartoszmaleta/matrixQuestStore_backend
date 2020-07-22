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
        System.out.println("qweqwe");
        this.connectionFactory = connectionFactory;
        this.studentDetailsDao = new StudentDetailsDaoDb(connectionFactory);
    }

    public User readUserByEmailAndPassword(String userEmail, String userPassword) {
        User newUser;

        try {
            ResultSet rs;

            rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"email\" = '" + userEmail + "' AND \"password\" = '" + userPassword + "';");

            newUser = getUser(rs);
            if (newUser != null) {
                return newUser;
            }
            connectionFactory.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
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
//                System.out.println("while rs = " + module);
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

        int id = -100; // Definitely invalid number

        try {
//            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"email\" = '" + email + "';");
//
            ps = connectionFactory.getConnection().prepareStatement("SELECT * FROM \"users\" WHERE \"email\" = '" + email + "';");
            ResultSet rs = ps.executeQuery();

            rs.next();
            id = rs.getInt("id");
            System.out.println("id rs = " + id);

            connectionFactory.close();
            rs.close();
            return id;
        } catch (Exception e) {
            System.err.println("Error! Reading user by id from DB failed!");
            return id;
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

    // TODO: this is better version of the above!! TEST FIRST
    public List getAllElements2() {
        List<User> users = new ArrayList<>();
        try {
            User newUser;
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users ORDER BY id;");
            while (rs.next()) {
                newUser = getUser(rs);
                if (newUser != null) {
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
        User newUser;

        try {
            ResultSet rs;

            rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"id\" = '" + id + "';");

            newUser = getUser(rs);
            if (newUser != null) {
                return newUser;
            }
            connectionFactory.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
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
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM users WHERE id = '" + id + "';");
            return ps.executeUpdate() != 0;
//            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudentDetails(int id) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM Student_Details WHERE student_id = '" + id + "';");
            ps.executeUpdate();

            ps.close();
            connectionFactory.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM users WHERE id = '" + id + "' AND " + "role_id = 3" + ";");
            ps.executeUpdate();


            ps.close();
            connectionFactory.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}