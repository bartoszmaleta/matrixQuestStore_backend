package com.company.dao;

import com.company.model.Transaction;
import com.company.model.user.User;

import java.util.List;

public interface TransactionDao extends Dao<Transaction> {
    List getTransactionsCountAndTotalSumByUser();

    List<Transaction> getMyTransactions(User user);

    List<Transaction> getMyTransactionsById(int studentId);
}
