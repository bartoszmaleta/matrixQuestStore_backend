package com.company.dao;

import com.company.model.Quest;
import com.company.model.user.User;

import java.util.List;

public interface QuestDao extends Dao<Quest> {
    List<Quest> readQuestListByMentor(User user);
    void updateQuestIdMentorById(int id, int mentorId);
    void updateQuestCoinsById(int id, int amountOfCoins);
    void updateQuestDescriptionById(int id, String description);
    void updateQuestTitleById(int id, String title);
    List<Quest> readQuestListByMentorById(int userIdStr);
}
