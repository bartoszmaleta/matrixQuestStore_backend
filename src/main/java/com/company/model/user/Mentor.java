package com.company.model.user;

public class Mentor extends User{


//    public Mentor(int id, String name, String surname, String login, String password, String email, int roleId) {
//        super(id, name, surname, login, password, email, roleId);
//    }
//
    public Mentor() {
    }


    public Mentor(int id, String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(id, name, surname, login, password, email, roleId, avatarSource);
    }

    public Mentor(String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(name, surname, login, password, email, roleId, avatarSource);
    }

}
