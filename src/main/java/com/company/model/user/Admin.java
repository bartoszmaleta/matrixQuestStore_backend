package com.company.model.user;

public class Admin extends User {

    private static final int ADMIN_ROLE_ID = 1;

    public Admin(int id, String name, String surname, String login, String password, String email, String avatarSource) {
        super(id, name, surname, login, password, email, ADMIN_ROLE_ID, avatarSource);
    }

    public Admin(int id, String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(id, name, surname, login, password, email, roleId, avatarSource);
    }

    public Admin(String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(name, surname, login, password, email, roleId, avatarSource);
    }
}
