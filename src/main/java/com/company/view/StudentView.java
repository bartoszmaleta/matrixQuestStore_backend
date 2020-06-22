package com.company.view;

import com.company.models.Transaction;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.FileNotFoundException;
import java.util.List;

public class StudentView {

    public static void displayTransactions(List<Transaction> transactions) {
    }

    public void showMenu() {
        System.out.println(
                "(1) Display my awards\n" +
                        "(2) Buy awards\n" +
                        "(3) Display my transactions\n" +
                        "(4) My profile\n" +
                        "(5) Display possible quests\n" +
                        "(0) Quit");
    }


    public static void allTransactionsFromList(List<Transaction> newList) throws FileNotFoundException {
        String[] headers = {"id", "student", "award title", "price", "date of purchase"};
        Object[][] data = new Object[newList.size()][headers.length];

        for (int i = 0; i < newList.size(); i++) {
            Transaction transaction = newList.get(i);
            data[i][0] = transaction.getTransactionId();
            data[i][1] = transaction.getBuyer();
            data[i][2] = transaction.getAwardTitle();
            data[i][3] = transaction.getPrice();
            data[i][4] = transaction.getDate();
        }
        System.out.println("All my transactions: ");
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }
}
