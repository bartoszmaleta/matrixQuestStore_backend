package com.company.model.user;

import com.company.model.Award;
import com.company.model.Quest;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{
//    private Wallet wallet;
    private List<Award> awardsList;     //TODO: to have awards
    private List<Quest> questsList;     //TODO: to have quests
    private String name;
    private String surname;

//    // TODO: Remove in futuer
//    public Student(String login, String password, String email, Role role, String name, String surname, String avatarSource) {
//        super(login, password, email, role, name, surname, avatarSource);
//    }

    // Constructor with id
    public Student(int id, String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(id, name, surname, login, password, email, roleId, avatarSource);
    }
    // POSTMAN!!
//    Constructor without id
    public Student(String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(name, surname, login, password, email, roleId, avatarSource);
    }



    public Student() {
    }

    public void setAwardsList(ArrayList<Award> awardsList) {
        this.awardsList = awardsList;
    }

    public void setQuestsList(ArrayList<Quest> questsList) {
        this.questsList = questsList;
    }

    public List<Award> getAwardsList() {
        return awardsList;
    }

    public List<Quest> getQuestsList() {
        return questsList;
    }
}
