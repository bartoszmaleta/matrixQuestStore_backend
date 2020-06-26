package com.company.model.statistics;

public class AwardCountByMentor {
    private String mentorNameAndSurname;
    private int amountOfAwardsCount;

    public AwardCountByMentor(String mentorNameAndSurname, int amountOfAwardsCount) {
        this.mentorNameAndSurname = mentorNameAndSurname;
        this.amountOfAwardsCount = amountOfAwardsCount;
    }

    public String getMentorNameAndSurname() {
        return mentorNameAndSurname;
    }

    public AwardCountByMentor setMentorNameAndSurname(String mentorNameAndSurname) {
        this.mentorNameAndSurname = mentorNameAndSurname;
        return this;
    }

    public int getAmountOfAwardsCount() {
        return amountOfAwardsCount;
    }

    public AwardCountByMentor setAmountOfAwardsCount(int amountOfAwardsCount) {
        this.amountOfAwardsCount = amountOfAwardsCount;
        return this;
    }
}
