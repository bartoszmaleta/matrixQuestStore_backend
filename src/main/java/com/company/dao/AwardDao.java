package com.company.dao;

import com.company.model.Award;
import com.company.model.user.User;

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

    List<Award> readAwardListByMentorById(int id);
}
