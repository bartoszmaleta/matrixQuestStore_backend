package com.company.models.users;

import com.company.models.Award;
import com.company.models.Quest;
import com.company.models.Wallet;

import java.util.ArrayList;

public class Student extends User{
    private Wallet wallet;
    private ArrayList<Award> awardsList;
    private ArrayList<Quest> questsList;
    private String name;
    private String surname;

    public Student(String login, String password, String email, Role role, String name, String surname, int user_detail_id) {
        super(login, password, email, role, name, surname, user_detail_id);
    }

    public Student(int id, String name, String surname, String login, String password, String email, int roleId) {
        super(id, name, surname, login, password, email, roleId);
    }

    public Student() {
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setAwardsList(ArrayList<Award> awardsList) {
        this.awardsList = awardsList;
    }

    public void setQuestsList(ArrayList<Quest> questsList) {
        this.questsList = questsList;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public ArrayList<Award> getAwardsList() {
        return awardsList;
    }

    public ArrayList<Quest> getQuestsList() {
        return questsList;
    }
}
