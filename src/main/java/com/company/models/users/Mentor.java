package com.company.models.users;

public class Mentor extends User{


    public Mentor(int id, String name, String surname, String login, String password, String email, int roleId) {
        super(id, name, surname, login, password, email, roleId);
    }

    public Mentor() {
    }


//    public Mentor(int id, String name, String surname, String login, String password, String email, Role role) {
//        super(id, name, surname, login, password, email, role);
//    }
}
