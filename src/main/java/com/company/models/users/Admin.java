package com.company.models.users;

public class Admin extends User{

    public Admin(String login, String password, String email, Role role, String name, String surname, int user_detail_id) {
        super(login, password, email, role, name, surname, user_detail_id);
    }

    public Admin() {
        super();
    }

    public Admin(int id, String name, String surname, String login, String password, String email, int roleId) {
        super(id, name, surname, login, password, email, roleId);
    }
}
