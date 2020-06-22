package com.company.view;

import com.company.models.Quest;
import com.company.models.statistics.TransactionCountAndTotalSumByUser;
import com.company.models.users.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.util.List;

public class TransactionsView {
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
}
