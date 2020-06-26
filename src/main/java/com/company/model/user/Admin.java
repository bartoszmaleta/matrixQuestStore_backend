package com.company.model.user;

public class Admin extends User{

    public Admin() {
        super();
    }

    public Admin(int id, String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(id, name, surname, login, password, email, roleId, avatarSource);
    }

    public Admin(String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(name, surname, login, password, email, roleId, avatarSource);
    }
}
