package com.company.models.users;

import com.company.models.Award;
import com.company.models.Quest;
import com.company.models.Wallet;

import java.util.ArrayList;

public class Student extends User{
    private Wallet wallet;
    private ArrayList<Award> awardsList;
    private ArrayList<Quest> questsList;

    public Student(String login, String password, String email, Role role, String name, String surname, int user_detail_id) {
        super(login, password, email, role, name, surname, user_detail_id);
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
