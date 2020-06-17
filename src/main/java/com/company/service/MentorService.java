package com.company.service;

import com.company.dao.AwardDAO;
import com.company.dao.QuestDAO;
import com.company.models.Award;
import com.company.models.Quest;
import com.company.view.View;

import java.io.FileNotFoundException;
import java.util.List;


public class MentorService extends EmployeeService {
    private final QuestDAO questDAO;
    private final AwardDAO awardDAO;

    public MentorService() {
        this.questDAO = new QuestDAO();
        this.awardDAO = new AwardDAO();
    }

    public void addQuestToDatabase(Quest quest) {
        questDAO.addQuest(quest);
    }

    public void updateQuestTitleById(int id, String titile) {
        questDAO.updateQuestTitleById(id, titile);
    }

    public void updateQuestDescriptionById(int id, String description) {
        questDAO.updateQuestDescriptionById(id, description);
    }

    public void updateQuestCoinsById(int id, int amountOfCoins) {
        questDAO.updateQuestCoinsById(id, amountOfCoins);
    }

    public void updateQuestIdMentorById(int id, int newId) {
        questDAO.updateQuestIdMentorById(id, newId);
    }

    public void deleteQuestById(int id) {
        questDAO.deleteQuestById(id);
    }

    public void readAllQuestsOrderById() {
        questDAO.readAllQuestsOrderById();
    }

    public void addAwardToDatabase(Award award) {
        awardDAO.addAward(award);
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

    public void readAllAwardsOrderById() throws FileNotFoundException {
        List<Award> awards = awardDAO.readAwardList();
        View.viewAllAwardsFromList(awards);
    }
}
