package com.company.service;

import com.company.dao.AwardDaoDb;
import com.company.dao.QuestDaoDb;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.Award;
import com.company.model.Quest;
import com.company.model.user.User;
import com.company.view.AwardsView;
import com.company.view.QuestsView;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class MentorService extends EmployeeService {
    private final QuestDaoDb questDaoDb;
    private final AwardDaoDb awardDAO;
    private final UserDao userDao;

    public MentorService() {
        this.questDaoDb = new QuestDaoDb();
        this.awardDAO = new AwardDaoDb();
        this.userDao = new UserDaoDb();
    }

    public void addQuestToDatabase(Quest quest) {
        questDaoDb.insert(quest);
//        questDAO.addQuest(quest);
    }

    public void updateQuestTitleById(int id, String titile) {
        questDaoDb.updateQuestTitleById(id, titile);
    }

    public void updateQuestDescriptionById(int id, String description) {
        questDaoDb.updateQuestDescriptionById(id, description);
    }

    public void updateQuestCoinsById(int id, int amountOfCoins) {
        questDaoDb.updateQuestCoinsById(id, amountOfCoins);
    }

    public void updateQuestIdMentorById(int id, int newId) {
        questDaoDb.updateQuestIdMentorById(id, newId);
    }

    public void deleteQuestById(int id) {
//        questDAO.deleteQuestById(id);
        questDaoDb.delete(id);
    }

    public void addAwardToDatabase(User user) {
        String awardTitle = InputTaker.takeStringInputWithMessage("Enter title of award: ");
        String awardDescription = InputTaker.takeStringInputWithMessage("Enter description of award: ");
        int awardPrice = InputTaker.takeIntInputWithMessage("Enter price of award: ");
        String awardImage = InputTaker.takeStringInputWithMessage("Enter image name: ");
        int awardCreatorId = user.getId();

        Date date = new Date();
        Award awardToAdd = new Award(awardTitle, awardDescription, awardPrice, awardImage, new Timestamp(date.getTime()), awardCreatorId);

        awardDAO.addAward(awardToAdd);
    }

    public void updateAwardTitleById(int id, String title) {
        awardDAO.updateAwardTitleById(id, title);
    }

    public void updateAwardDescriptionById(int id, String description) {
        awardDAO.updateAwardDescriptionById(id, description);
    }

    public void updateAwardPriceById(int id, int price) {
        awardDAO.updateAwardPriceById(id, price);
    }

    public void updateAwardCreatorIdById(int id, int newId) {
        awardDAO.updateAwardCreatorIdById(id, newId);
    }

    public void deleteAwardById(int id) {
        awardDAO.deleteAwardById(id);
    }

    public void displayAllAwards() throws FileNotFoundException {
        List<Award> awards = this.awardDAO.getAllElements();
        AwardsView.allAwardsByList(awards);
    }

    public void displayAllAwardsOfThisMentor(User user) throws FileNotFoundException {
        List<Award> awards = awardDAO.readAwardListByMentor(user);
        AwardsView.allAwardsByList(awards);
    }

    public void displayAllQuestsOfThisMentor(User user) throws FileNotFoundException {
        List<Quest> quests = this.questDaoDb.readQuestListByMentor(user);
        QuestsView.allQuestsByList(quests);
    }

    public void displayAllQuests() throws FileNotFoundException {
        List<Quest> quests = this.questDaoDb.getAllElements();
        QuestsView.allQuestsByList(quests);
    }

}
