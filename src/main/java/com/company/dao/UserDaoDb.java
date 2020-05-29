package com.company.dao;

import com.company.models.users.*;

import java.sql.*;

public class UserDaoDb {
    ConnectionFactory connectionFactory;

    public UserDaoDb() {
        this.connectionFactory = new ConnectionFactory();
    }

    public User readUserByEmailAndPassword(String userEmail, String userPassword) {

        Connection c = null;
        User newUser;

        try {
            System.out.println("\nI am in readUserByNameAndPassword\n");
            ConnectionFactory connectionFactory = new ConnectionFactory();
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM \"Users\" WHERE \"email\" = '" + userEmail + "' AND \"password\" = '" + userPassword + "';");

            if (rs.next() && (rs.getInt("role_id") == 1)) {
                newUser = new Admin();

                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int role_id = rs.getInt("role_id");

                newUser.setId(id);
                newUser.setLogin(login);
                newUser.setPassword(password);
                newUser.setEmail(email);
                newUser.setRoleEnum(role_id);

                // TODO: where close()?????
                connectionFactory.close();
                rs.close();

                return newUser;

            } else if (rs.next() && (rs.getInt("role_id") == 2)) {
//                newUser = new Mentor();
            } else if (rs.next() && (rs.getInt("role_id") == 3)) {
//                newUser = new Student();
            } else {
                System.out.println("No user");
            }

            // TODO: where close()?????
//            connectionFactory.close();
//            rs.close();
        } catch (Exception e) {
            System.err.println("Error! Reading user by userName and userPassword from DB failed!");
        }
        return null;
    }

    public void addUserToDatabase(User user) {
        PreparedStatement ps = null;
        Role userRole = user.getRole();

        int roleId = switch (userRole) {
            case ADMIN -> 1;
            case MENTOR -> 2;
            case STUDENT -> 3;
        };

        try {
            ps = connectionFactory.getConnection().prepareStatement("INSERT INTO users (name, surname, login, password, email, role_id, user_detail_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setInt(6, roleId);
            ps.setInt(7, user.getUser_detail_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int id) {
        PreparedStatement ps = null;

        try {
            ps = connectionFactory.getConnection().prepareStatement("DELETE FROM users WHERE id =" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        PreparedStatement ps = null;
        Role userRole = user.getRole();

        int roleId = switch (userRole) {
            case ADMIN -> 1;
            case MENTOR -> 2;
            case STUDENT -> 3;
        };

        try {
            ps = connectionFactory.getConnection().prepareStatement("UPDATE users name='" + user.getName()+"'" + ", surname='" + user.getSurname()+ "'" + ", login='" + user.getLogin()+"'" +", password='" + user.getPassword() + "'" + ", email='" + user.getEmail()+ "'" + ", role_id=" + roleId + ", user_detail_id=" + 1 + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readUsers() {
        try {
            ResultSet rs = connectionFactory.executeQuery("SELECT * FROM users ORDER BY id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int roleId = rs.getInt("role_id");
                int userDetailsId = rs.getInt("user_detail_id");

                String format = "|%1$-4s|%2$-15s|%3$-15s|%4$-15s|%5$-20s|%6$-25s|%7$-7s|%8$-7s\n";
                System.out.printf(format, id, name, surname, login, password, email, roleId, userDetailsId);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
}
