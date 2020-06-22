package com.company.dao;

import com.company.models.Award;
import com.company.models.users.User;

import java.util.List;

public interface AwardDao extends Dao {
    void updateAwardCreatorIdById(int id, int creatorId);
    void updateAwardPriceById(int id, int price);
    void updateAwardDescriptionById(int id, String description);
    void updateAwardTitleById(int id, String title);
    void addAward(Award award);
    void deleteAwardById(int id);
    List<Award> readAwardListByMentor(User user);
    List<Award> readAwardListWithMentors();

    List<Award> getAwardsByUser(User user);
}
