package com.company.models.users;

import com.company.models.Award;
import com.company.models.Quest;
import com.company.models.Wallet;

import java.util.ArrayList;

public class Student extends User{
    private Wallet wallet;
    private ArrayList<Award> awardsList;
    private ArrayList<Quest> questsList;


    public Student(int id, String login, String password, String email, Role role) {
        super(id, login, password, email, role);
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
