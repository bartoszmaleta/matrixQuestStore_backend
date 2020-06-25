package com.company.dao;

import com.company.models.users.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDb implements UserDao {
    ConnectionFactory connectionFactory;

    public UserDaoDb() {
        this.connectionFactory = new ConnectionFactory();
    }

    public User readUserByEmailOrLoginAndPassword(String userEmail, String userPassword) {
        Connection c = null;
        User newUser;

        try {
            System.out.println("\nI am in readUserByNameAndPassword\n");
            ResultSet rs;

            // TODO: find User by login!
//            ResultSet rs2 = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"email\" = '" + userEmail + "' AND \"password\" = '" + userPassword + "';");
//            ResultSet rs3 = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"login\" = '" + userEmail + "' AND \"password\" = '" + userPassword + "';");
//
//            System.out.println("rs2 = " + rs2);
//            System.out.println("rs3 = " + rs3);
//
//            if (rs2 == null) {
//                rs = rs3;
//            } else {
//                rs = rs2;
//            }

            // SIMPLIER OPTION
            rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"email\" = '" + userEmail + "' AND \"password\" = '" + userPassword + "';");

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
            // TODO: where close()?????
            connectionFactory.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
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

        User newUser = new Student(id, name, surname, login, password, email, roleId, avatarPath);

        connectionFactory.close();
        rs.close();

        return newUser;
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
//        System.out.println("simple name = " + newUser.getClass().getSimpleName());
        System.out.println("role = " + newUser.getRole());
        // TODO: where close()?????
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
            while (rs.next()) {
                // TODO: should initialize eariler???
//                User newUser = new Student();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int roleId = rs.getInt("role_id");
                String avatarPath = rs.getString("avatar");

                User newUser = new Student(id, name, surname, login, password, email, roleId, avatarPath);

//                User student = getStudent(rs); TODO: change to this, because it is enough!!!

                students.add(newUser);
            }
            rs.close();
            connectionFactory.close();
            // TODO: PrepareStatement also need to be closed!!

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
//                User newUser = new Mentor();
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
    public boolean edit(Object o) {
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
        Connection c = null;

        try {
            System.out.println("\nI am in readUserIdByEmail\n");

            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"email\" = '" + email + "';");

            rs.next();
            int id = rs.getInt("id");
            System.out.println("id from db = " + id);

            connectionFactory.close();
            rs.close();
            return id;
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
            return 0;
        }
    }

    // NOT USED
    @Override
    public User readUserByEmail(String userEmail) {
        Connection c = null;
        User newUser;

        try {
            System.out.println("\nI am in readUserByEmail\n");

            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"email\" = '" + userEmail + "';");

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
            connectionFactory.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
        }
        return null;
    }

    @Override
    public User readUserById(int userId) {
        Connection c = null;
        User newUser;

        try {

            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM \"users\" WHERE \"id\" = '" + userId + "';");

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
            connectionFactory.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
        }
        return null;
    }


    // ---------------------------------------


    @Override
    public List getAllElements() {
        List<User> users = new ArrayList<>();
        try {
//            User newUser;
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users ORDER BY id;");
            while (rs.next()) {
//                User newUser = new User();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int roleId = rs.getInt("role_id");
                String avatarSource = rs.getString("avatar");

                if (roleId == 1) {
                    User newUser = new Admin(id, name, surname, login, password, email, roleId, avatarSource);
                    users.add(newUser);
                } else if (roleId == 2) {
                    User newUser = new Mentor(id, name, surname, login, password, email, roleId, avatarSource);
                    users.add(newUser);
                } else if (roleId == 3) {
                    User newUser = new Student(id, name, surname, login, password, email, roleId, avatarSource);
                    users.add(newUser);
                }
            }
            rs.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean insert(Object o) {
        User user = (User) o;
        PreparedStatement ps = null;
        Role userRole = user.getRole();

        int roleId = decideRole(userRole);
        // TODO: DONE
        // in users ==> user_detail_id REMOVE
        // in users ==> avatar ADD
        // in user_details ==> avatar REMOVE
        // in user_details ==> user_id ADD

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
            ps.executeUpdate();
            return true;
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //-----------------------------------------------------------------
    public boolean deleteMentorOrStudent(int id, int role_id) {
        PreparedStatement ps = null;
        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM users WHERE id =" + id + " AND " + "role_id = " + role_id + ";");
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//-----------------------------------------------------------------
//    public void addUserToDatabase(User user) {
//        PreparedStatement ps = null;
//        Role userRole = user.getRole();
//
//        int roleId = decideRole(userRole);
//
//        try {
//            ps = connectionFactory.getConnection().prepareStatement("INSERT INTO users (name, surname, login, password, email, role_id, user_detail_id)" +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getSurname());
//            ps.setString(3, user.getLogin());
//            ps.setString(4, user.getPassword());
//            ps.setString(5, user.getEmail());
//            ps.setInt(6, roleId);
//            ps.setInt(7, user.getUser_detail_id());
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    //    public void deleteUserById(int id) {
//        PreparedStatement ps = null;
//
//        try {
//            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM users WHERE id =" + id + ";");
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public void updateUser(User user) {
//        PreparedStatement ps = null;
//        Role userRole = user.getRole();
//
//        int roleId = decideRole(userRole);
//
//        try {
//            ps = connectionFactory.getConnection().prepareStatement("UPDATE users name='" + user.getName()+"'" + ", surname='" + user.getSurname()+ "'" + ", login='" + user.getLogin()+"'" +", password='" + user.getPassword() + "'" + ", email='" + user.getEmail()+ "'" + ", role_id=" + roleId + ", user_detail_id=" + 1 + ";");
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//    }

//    public void readUsers() {
//        try {
//            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users ORDER BY id;");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String surname = rs.getString("surname");
//                String login = rs.getString("login");
//                String password = rs.getString("password");
//                String email = rs.getString("email");
//                int roleId = rs.getInt("role_id");
//                int userDetailsId = rs.getInt("user_detail_id");
//
//                String format = "|%1$-4s|%2$-15s|%3$-15s|%4$-15s|%5$-20s|%6$-25s|%7$-7s|%8$-7s\n";
//                System.out.printf(format, id, name, surname, login, password, email, roleId, userDetailsId);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//
//    public List<User> readAllUsers() {
//        List<User> users = new ArrayList<>();
//        try {
////            User newUser;
//            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users ORDER BY id;");
//            while (rs.next()) {
////                User newUser = new User();
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String surname = rs.getString("surname");
//                String login = rs.getString("login");
//                String password = rs.getString("password");
//                String email = rs.getString("email");
//                int roleId = rs.getInt("role_id");
//                int userDetailsId = rs.getInt("user_detail_id");
//
//                if (roleId == 1) {
//                    User newUser = new Admin(id, name, surname, login, password, email, roleId);
//                    users.add(newUser);
//                } else if (roleId == 2) {
//                    User newUser = new Mentor(id, name, surname, login, password, email, roleId);
//                    users.add(newUser);
//                } else if (roleId == 3) {
//                    User newUser = new Student(id, name, surname, login, password, email, roleId);
//                    users.add(newUser);
//                }
//            }
//            rs.close();
//            return users;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
