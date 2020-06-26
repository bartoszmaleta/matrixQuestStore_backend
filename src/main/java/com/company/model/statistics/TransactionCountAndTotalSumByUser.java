package com.company.model.statistics;

public class TransactionCountAndTotalSumByUser {
    private String studentNameAndSurname;
    private int transactionCount;
    private int totalSpent;

    public TransactionCountAndTotalSumByUser(String studentNameAndSurname, int transactionCount, int totalSpent) {
        this.studentNameAndSurname = studentNameAndSurname;
        this.transactionCount = transactionCount;
        this.totalSpent = totalSpent;
    }

    public String getStudentNameAndSurname() {
        return studentNameAndSurname;
    }

    public TransactionCountAndTotalSumByUser setStudentNameAndSurname(String studentNameAndSurname) {
        this.studentNameAndSurname = studentNameAndSurname;
        return this;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public TransactionCountAndTotalSumByUser setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
        return this;
    }

    public int getTotalSpent() {
        return totalSpent;
    }

    public TransactionCountAndTotalSumByUser setTotalSpent(int totalSpent) {
        this.totalSpent = totalSpent;
        return this;
    }
}
