package com.company.service;

import com.company.dao.StatisticsDao;
import com.company.dao.StatisticsDaoDb;
import com.company.dao.TransactionDao;
import com.company.dao.TransactionDaoDb;
import com.company.models.statistics.AwardCountByMentor;
import com.company.models.statistics.QuestCountByMentor;
import com.company.models.statistics.TransactionCountAndTotalSumByUser;
import com.company.view.StatisticsView;

import java.util.List;

public class StatisticsService {
    private StatisticsDao statisticsDao;
    private TransactionDao transactionDao;

    public StatisticsService() {
        this.statisticsDao = new StatisticsDaoDb();
        this.transactionDao = new TransactionDaoDb();
    }

    public void displayTransactionsCountsAndTotalSumByUser() {
        List<TransactionCountAndTotalSumByUser> stats = transactionDao.getTransactionsCountAndTotalSumByUser();
        StatisticsView.countAndTotalByUser(stats);
    }

    public void displayQuestsCountByMentor() {
        List<QuestCountByMentor> stats = statisticsDao.getQuestCountByMentor();
        StatisticsView.countQuestByMentor(stats);
    }


    public void displayAwardsCountByMentor() {
        List<AwardCountByMentor> stats = statisticsDao.getAwardCountByMentor();
        StatisticsView.countAwardsByMentor(stats);
    }
}
