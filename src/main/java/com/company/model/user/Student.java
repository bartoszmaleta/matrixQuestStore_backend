package com.company.model.user;

import com.company.model.Award;
import com.company.model.Quest;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{
//    private Wallet wallet;
    private List<Award> awardsList;     //TODO: to have awards
    private List<Quest> questsList;     //TODO: to have quests
    private int coins;
    private String module;
    private String personalMentor;

    public String getModule() {
        return module;
    }

    public Student setModule(String module) {
        this.module = module;
        return this;
    }

    public String getPersonalMentor() {
        return personalMentor;
    }

    public Student setPersonalMentor(String personalMentor) {
        this.personalMentor = personalMentor;
        return this;
    }

    // Constructor with id
    public Student(int id, String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(id, name, surname, login, password, email, roleId, avatarSource);
    }

    // Constructor with coins
    public Student(int id, String name, String surname, String login, String password, String email, int roleId, String avatarSource, int coins, String module, String personalMentor) {
        super(id, name, surname, login, password, email, roleId, avatarSource);
        this.coins = coins;
        this.module = module;
        this.personalMentor = personalMentor;
    }

//    Constructor without id
    public Student(String name, String surname, String login, String password, String email, int roleId, String avatarSource) {
        super(name, surname, login, password, email, roleId, avatarSource);
    }


    public Student setAwardsList(List<Award> awardsList) {
        this.awardsList = awardsList;
        return this;
    }

    public Student setQuestsList(List<Quest> questsList) {
        this.questsList = questsList;
        return this;
    }

    public int getCoins() {
        return coins;
    }

    public Student setCoins(int coins) {
        this.coins = coins;
        return this;
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
