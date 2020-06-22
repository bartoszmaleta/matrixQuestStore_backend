package com.company.models.statistics;

public class QuestCountByMentor {
    private String mentorNameAndSurname;
    private int amountOfQuestsCount;

    public QuestCountByMentor(String mentorNameAndSurname, int amountOfQuestsCount) {
        this.mentorNameAndSurname = mentorNameAndSurname;
        this.amountOfQuestsCount = amountOfQuestsCount;
    }

    public String getMentorNameAndSurname() {
        return mentorNameAndSurname;
    }

    public QuestCountByMentor setMentorNameAndSurname(String mentorNameAndSurname) {
        this.mentorNameAndSurname = mentorNameAndSurname;
        return this;
    }

    public int getAmountOfQuestsCount() {
        return amountOfQuestsCount;
    }

    public QuestCountByMentor setAmountOfQuestsCount(int amountOfQuestsCount) {
        this.amountOfQuestsCount = amountOfQuestsCount;
        return this;
    }
}
