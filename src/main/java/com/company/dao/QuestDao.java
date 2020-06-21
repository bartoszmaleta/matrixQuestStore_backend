package com.company.dao;

import com.company.models.Quest;
import com.company.models.users.User;

import java.util.List;

public interface QuestDao extends Dao{
    List<Quest> readQuestListByMentor(User user);
    void updateQuestIdMentorById(int id, int mentorId);
    void updateQuestCoinsById(int id, int amountOfCoins);
    void updateQuestDescriptionById(int id, String description);
    void updateQuestTitleById(int id, String title);
}
