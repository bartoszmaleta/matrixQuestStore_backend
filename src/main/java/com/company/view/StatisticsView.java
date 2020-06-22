package com.company.view;

import com.company.models.Quest;
import com.company.models.statistics.AwardCountByMentor;
import com.company.models.statistics.QuestCountByMentor;
import com.company.models.statistics.TransactionCountAndTotalSumByUser;
import com.company.models.users.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.util.List;

public class StatisticsView {
    public static void countAndTotalByUser(List<TransactionCountAndTotalSumByUser> stats) {
        String[] headers = {"Student", "Amount of transactions", "Sum of transactions"};
        Object[][] data = new Object[stats.size()][headers.length];

        for (int i = 0; i < stats.size(); i++) {
            TransactionCountAndTotalSumByUser stat = stats.get(i);
            data[i][0] = stat.getStudentNameAndSurname();
            data[i][1] = stat.getTransactionCount();
            data[i][2] = stat.getTotalSpent();
        }
        System.out.println("Transaction count and total sum spend by students");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public static void countQuestByMentor(List<QuestCountByMentor> stats) {
        String[] headers = {"Mentor", "Amount of quests added"};
        Object[][] data = new Object[stats.size()][headers.length];

        for (int i = 0; i < stats.size(); i++) {
            QuestCountByMentor stat = stats.get(i);
            data[i][0] = stat.getMentorNameAndSurname();
            data[i][1] = stat.getAmountOfQuestsCount();
        }
        System.out.println("Transaction count and total sum spend by students");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

    public static void countAwardsByMentor(List<AwardCountByMentor> stats) {
        String[] headers = {"Mentor", "Amount of quests added"};
        Object[][] data = new Object[stats.size()][headers.length];

        for (int i = 0; i < stats.size(); i++) {
            AwardCountByMentor stat = stats.get(i);
            data[i][0] = stat.getMentorNameAndSurname();
            data[i][1] = stat.getAmountOfAwardsCount();
        }
        System.out.println("Transaction count and total sum spend by students");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }
}
