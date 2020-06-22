package com.company.dao;

import java.util.List;

public interface TransactionDao extends Dao {
    List getTransactionsCountAndTotalSumByUser();
}
