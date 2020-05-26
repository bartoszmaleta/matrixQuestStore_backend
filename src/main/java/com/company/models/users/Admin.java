package com.company.models.users;

public class Admin extends User{

    public Admin(int id, String login, String password, String email, Role role) {
        super(id, login, password, email, role);
    }
}
