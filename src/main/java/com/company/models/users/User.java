package com.company.models.users;

import com.company.models.Transaction;

import java.util.List;

public abstract class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private String name;
    private String surname;
    private int user_detail_id;
    private List<Transaction> transactions;

    public User(String login, String password, String email, Role role, String name, String surname, int user_detail_id) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.user_detail_id = user_detail_id;
    }

    public User() {
    }

    public User(int id, String login, String password, String email, Role role) {
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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRoleEnum(int userNumber) {
        if (userNumber == 1) {
            this.role = Role.ADMIN;
        } else if (userNumber == 2) {
            this.role = Role.MENTOR;
        } else if (userNumber == 3) {
            this.role = Role.STUDENT;
        } else {
            System.out.println("Wrong userNumber");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getUser_detail_id() {
        return user_detail_id;
    }

    public void setUser_detail_id(int user_detail_id) {
        this.user_detail_id = user_detail_id;
    }
}
