package com.company.service;

import com.company.dao.*;
import com.company.model.Award;
import com.company.model.Quest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MentorServiceTest {

    private static MentorService mentorService;
    private static QuestDaoDb questDaoDb;
    private static AwardDaoDb awardDaoDb;

    @BeforeAll
    static void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
                , "org.postgresql.Driver"
                , "pirqathgcgzhbg"
                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
        );
        mentorService = new MentorService(connectionFactory);
        questDaoDb = Mockito.mock(QuestDaoDb.class);
        awardDaoDb = Mockito.mock(AwardDaoDb.class);
    }

    @Test
    public void should_returnQuests_when_mentorsIdIsProvided() {
        List<Quest> questsExpected = new ArrayList<>();
        Quest quest = new Quest(1, "Dodge This!", "Dodge 5x times morning question", 5, "dodgeThis.jpg", 2);
        questsExpected.add(quest);

        Mockito.when(questDaoDb.readQuestListByMentorById(2))
                .thenReturn(questsExpected);

        assertEquals(questDaoDb.readQuestListByMentorById(2), mentorService.getAllQuestsOfThisMentorByUserId(2));
    }

    @Test
    public void should_returnAwards_when_mentorsIdIsProvided() {
        List<Award> awardsExpected = new ArrayList<>();
        Award award = new Award(1
                , "Nokia Software"
                , "1h programming course on oldstyle Nokia"
                , 20, "nokianeo.png"
                , Timestamp.valueOf("2020-04-27 10:54:49.000000")
                , 2);
        awardsExpected.add(award);

        Mockito.when(awardDaoDb.readAwardListByMentorById(2))
                .thenReturn(awardsExpected);

        List<Award> awsss = mentorService.getAllAwardsOfThisMentorByUserId(2);
        System.out.println("qweqwe" + awsss.get(0).getMentorId());

        assertEquals(awardDaoDb.readAwardListByMentorById(2)
                , mentorService.getAllAwardsOfThisMentorByUserId(2));
    }


}