package com.company.dao;

import com.company.models.Transaction;
import com.company.models.users.User;

import java.util.List;

public interface TransactionDao extends Dao {
    List getTransactionsCountAndTotalSumByUser();

    List<Transaction> getMyTransactions(User user);
}
