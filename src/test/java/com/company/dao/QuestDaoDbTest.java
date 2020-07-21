package com.company.dao;

import com.company.model.Quest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestDaoDbTest {
    private static QuestDaoDb questDaoDb;

    @BeforeEach
    public void setup() {
        questDaoDb = new QuestDaoDb();
    }


    @Test
    public void testGetQuestById() {
        Quest quest = new Quest()
        .setId(9)
        .setTitle("Best Trio")
        .setDescription("Stand on the podium in Kahoot")
        .setPrice(12)
        .setImageSrc("podium.jpg");

        assertEquals(quest, questDaoDb.getById(9));

    }

    @Test
    public void testUpdateQuestTitleByIdWhenAddingText() {
        String expected = "Destroy bugs for test";
        questDaoDb.updateQuestTitleById(3, "Destroy bugs for test");
        String actual = questDaoDb.getById(3).getTitle();
        assertEquals(expected, actual);

    }

    @Test
    public void testUpdateQuestTitleByIdWhenRemovingText() {
        String expected = "Destroy bugs";
        questDaoDb.updateQuestTitleById(3, "Destroy bugs");
        String actual = questDaoDb.getById(3).getTitle();
        assertEquals(expected, actual);
    }

}