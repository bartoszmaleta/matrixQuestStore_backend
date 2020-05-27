package com.company.models.users;

import com.company.models.Transaction;

import java.util.List;

public abstract class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private List<Transaction> transactions;

    public User(int id, String login, String password, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {
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
}
