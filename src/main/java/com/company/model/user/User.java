package com.company.model.user;

import com.company.model.Transaction;

import java.util.List;

public abstract class User {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private Role role;
    private List<Transaction> transactions;
    private String avatarSource;

    // Proper!!
    public User(int id, String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
//        this.role = role;
        setRoleEnum(roleId);
        this.avatarSource = avatarSource;
    }

    public User() {
    }

    // POSTMAN!!
    public User(String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
//        this.role = role;
        setRoleEnum(roleId);
        this.avatarSource = avatarSource;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public User setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public User setAvatarSource(String avatarSource) {
        this.avatarSource = avatarSource;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public User setRoleEnum(int userNumber) {
        if (userNumber == 1) {
            this.role = Role.ADMIN;
        } else if (userNumber == 2) {
            this.role = Role.MENTOR;
        } else if (userNumber == 3) {
            this.role = Role.STUDENT;
        } else {
            System.out.println("Wrong userNumber");
        }
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public String toString() {
        return this.name
                + " " + this.surname
                + " " + this.login
                + " " + this.password
                + " " + this.email
                + " " + this.role
                + " " + this.avatarSource;
    }

    public String getAvatarSource() {
        return avatarSource;
    }
}
