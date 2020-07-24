package com.company.dao;

import com.company.model.Quest;
import com.company.model.user.Mentor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestDaoDbTest {
    private static QuestDaoDb questDaoDb;

    @BeforeEach
    public void setup() {
            ConnectionFactory connectionFactory = new ConnectionFactory(
                    "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
                    , "org.postgresql.Driver"
                    , "pirqathgcgzhbg"
                    , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
            );
            questDaoDb = new QuestDaoDb();
    }

    @AfterEach
    public void cleanUp() {
        questDaoDb.cleanUpDatabase();
    }


    @Test
    public void testGetQuestById() {
        Quest quest = new Quest()
        .setId(3)
        .setTitle("Destroy bugs for test")
        .setDescription("Find and fix 3 bugs in someone else's program")
        .setPrice(7)
        .setImageSrc("bugs.jpg");
        assertEquals(quest, questDaoDb.getById(3));

    }

    @Test
    public void testUpdateQuestTitleByIdWhenAddingText() {
        String expected = "Destroy bugs for test";
        questDaoDb.updateQuestTitleById(8, "Destroy bugs for test");
        String actual = questDaoDb.getById(8).getTitle();
        assertEquals(expected, actual);

    }


    @Test
    public void testUpdateQuestDescriptionByIdWhenAdd(){
        String expected = "Review your friend's code for test";
        questDaoDb.updateQuestDescriptionById(7, "Review your friend''s code for test");
        String actual = questDaoDb.getById(7).getDescription();
        assertEquals(expected, actual);
    }


    @Test
    public void testUpdateCoinsByIdWhenBelowZero() {
        assertThrows(IllegalArgumentException.class, () ->questDaoDb.updateQuestCoinsById(3, -14), "Amount of coins can't be below 0");
    }

    @Test
    public void testUpdateQuestCoinsById() {
        questDaoDb.updateQuestCoinsById(6, 666);
        int actual = questDaoDb.getById(6).getPrice();
        assertEquals(666, actual);
    }

    @Test
    public void testUpdateQuestIdMentorById() { // TODO: change userId/mentorId, there is no mentor with id 22
        questDaoDb.updateQuestIdMentorById(2, 5);
        int actual = questDaoDb.getById(2).getMentorId();
        assertEquals(5, actual);
    }

    @Test
    public void testReadQuestListByMentorId() {
        List<Quest> expectedList = new ArrayList<>(Arrays.asList(questDaoDb.getById(2), questDaoDb.getById(6)));
        QuestDaoDb mockQuestDaoDb = Mockito.mock(QuestDaoDb.class);
        Mockito.when(mockQuestDaoDb.readQuestListByMentorById(1)).thenReturn(expectedList);

        List<Quest> mentorQuests = questDaoDb.readQuestListByMentorById(1);
        assertEquals(expectedList, mentorQuests);
        Mockito.verify(mockQuestDaoDb).readQuestListByMentorById(1);
    }

    @Disabled
    @Test
    public void testReadQuestListByMentor() {
        UserDaoDb mockUserDaoDb = Mockito.mock(UserDaoDb.class);
        Mockito.when(mockUserDaoDb.getById(1)).thenReturn(new Mentor(1, "Marek", "Zegarek", "marek11", "pass", "marek@op.pl", 2, null));
        System.out.println(mockUserDaoDb.getById(1));
        List<Quest> expectedList = new ArrayList<>(Arrays.asList(questDaoDb.getById(8), questDaoDb.getById(6)));

        QuestDaoDb mockquestDao = Mockito.mock(QuestDaoDb.class);
        Mockito.when(mockquestDao.readQuestListByMentorById(1)).thenReturn(expectedList);
        List<Quest> mentorQuests = mockquestDao.readQuestListByMentor(mockUserDaoDb.getById(1));

        assertEquals(expectedList, mentorQuests);
        Mockito.verify(mockUserDaoDb).getById(1);
    }

    @Test
    public void testGetAllQuests() {
        List<Quest> expectedList = new ArrayList<>(Arrays.asList(questDaoDb.getById(1),
                questDaoDb.getById(2), questDaoDb.getById(3), questDaoDb.getById(4),
                questDaoDb.getById(5), questDaoDb.getById(6),
                questDaoDb.getById(7), questDaoDb.getById(8), questDaoDb.getById(9)));
        List<Quest> allElements = questDaoDb.getAllElements();
        assertIterableEquals(expectedList, allElements);
    }

    @Test
    public void testInsertQuest() { // TODO: change userId/mentorId, there is no mentor with id 22
        Quest questToAdd = new Quest(10, "Test", "Description Test", 420, "test.jpg", 2);
        questDaoDb.insert(questToAdd);
        Quest actualQuest = questDaoDb.readQuestListByMentorById(2).get(0);
        assertEquals(questToAdd, actualQuest);
    }

    @Test
    public void testDeleteQuest() {
        questDaoDb.delete(5);
        assertNull(questDaoDb.getById(5));
    }
}