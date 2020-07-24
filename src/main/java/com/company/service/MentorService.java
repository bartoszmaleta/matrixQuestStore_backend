package com.company.service;

import com.company.dao.*;
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
    private final QuestDao questDao;
    private final AwardDao awardDAO;
    private final UserDao userDao;
    private ConnectionFactory connectionFactory;

    public MentorService() {
        this.questDao = new QuestDaoDb();
        this.awardDAO = new AwardDaoDb();
        this.userDao = new UserDaoDb();
    }

    public MentorService(ConnectionFactory connectionFactory) {
        this.userDao = new UserDaoDb();
        this.connectionFactory = connectionFactory;
        this.questDao = new QuestDaoDb(connectionFactory);
        this.awardDAO = new AwardDaoDb(connectionFactory);
    }

    public void addQuestToDatabase(Quest quest) {
        questDao.insert(quest);
    }

    public void addAwardToDatabase(Award award) {
        awardDAO.insert(award);
    }


    public void updateQuestTitleById(int id, String titile) {
        questDao.updateQuestTitleById(id, titile);
    }

    public void updateQuestDescriptionById(int id, String description) {
        questDao.updateQuestDescriptionById(id, description);
    }

    public void updateQuestCoinsById(int id, int amountOfCoins) {
        questDao.updateQuestCoinsById(id, amountOfCoins);
    }

    public void updateQuestIdMentorById(int id, int newId) {
        questDao.updateQuestIdMentorById(id, newId);
    }

    public void deleteQuestById(int id) {
        questDao.delete(id);
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
        awardDAO.delete(id);
    }

    public void displayAllAwards() throws FileNotFoundException {
        List<Award> awards = this.awardDAO.getAllElements();
        AwardsView.allAwardsByList(awards);
    }

    public void displayAllAwardsOfThisMentor(User user) throws FileNotFoundException {
        List<Award> awards = awardDAO.readAwardListByMentor(user);
        AwardsView.allAwardsByList(awards);
    }

    public List<Award> getAllAwardsOfThisMentorByUserId(int id) {
        // TODO: which version????
        //        List<Award> awards = awardDAO.readAwardListByMentor(user);

        List<Award> awards = awardDAO.readAwardListByMentorById(id);
        return awards;
    }

    public void displayAllQuestsOfThisMentor(User user) throws FileNotFoundException {
        List<Quest> quests = this.questDao.readQuestListByMentor(user);
        QuestsView.allQuestsByList(quests);
    }

    public void displayAllQuests() throws FileNotFoundException {
        List<Quest> quests = this.questDao.getAllElements();
        QuestsView.allQuestsByList(quests);
    }

    public List<Quest> getAllQuestsOfThisMentorByUserId(int idOfMentor) {
        List<Quest> quests = questDao.readQuestListByMentorById(idOfMentor);
        System.out.println(quests);
        return quests;
    }
}
