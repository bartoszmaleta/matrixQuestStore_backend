package com.company.dao;

import com.company.model.Transaction;
import com.company.model.statistics.TransactionCountAndTotalSumByUser;
import com.company.model.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoDb implements TransactionDao {

    private List<Transaction> transactions;
    private final ConnectionFactory conFactory;

    public TransactionDaoDb() {
        this.conFactory = new ConnectionFactory();
    }

    @Override
    public List getTransactionsCountAndTotalSumByUser() {
        List<TransactionCountAndTotalSumByUser> statistics = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT\n" +
                    "     (CONCAT(u.name, ' ', u.surname)) AS student_buyer\n" +
                    "    , COUNT(\"Transactions\".id) AS transaction_count\n" +
                    "    , CAST(SUM(\"Transactions\".price) AS FLOAT) AS total\n" +
                    "FROM \"Transactions\"\n" +
                    "JOIN users u\n" +
                    "ON u.id = \"Transactions\".user_id\n" +
                    "-- GROUP BY u.name\n" +
                    "GROUP BY student_buyer\n" +
                    "ORDER BY transaction_count DESC;");

            while (rs.next()) {
                String studentNameAndSurname = rs.getString("student_buyer");
                int transaction_count = rs.getInt("transaction_count");
                int total_amount = rs.getInt("total");

                TransactionCountAndTotalSumByUser stat = new TransactionCountAndTotalSumByUser(studentNameAndSurname
                , transaction_count
                , total_amount);
                statistics.add(stat);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statistics;
    }

    @Override
    public List<Transaction> getMyTransactions(User user) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Transactions\".id\n" +
                    "     , user_id\n" +
                    "     , (CONCAT(student.name, ' ', student.surname)) AS buyer\n" +
                    "     , created_at\n" +
                    "     , award_id\n" +
                    "     , aw.title\n" +
                    "     , \"Transactions\".price\n" +
                    "    FROM \"Transactions\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 3\n" +
                    "    ) student\n" +
                    "ON \"Transactions\".user_id = student.id\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM \"Awards\"\n" +
                    "        ) aw\n" +
                    "ON \"Transactions\".award_id = aw.id\n" +
                    "    WHERE user_id = " + user.getId() + "\n" +
                    "ORDER BY \"Transactions\".id;");

            while (rs.next()) {
                int transactionId = rs.getInt("id");
                int studentId = rs.getInt("user_id");
                String studentNameAndSurname = rs.getString("buyer");
                Timestamp dataCreation = rs.getTimestamp("created_at");
                int awardId = rs.getInt("award_id");
                String awardTitle = rs.getString("title");
                int price = rs.getInt("price");

                Transaction transaction = new Transaction(transactionId, studentId, studentNameAndSurname, dataCreation, awardId, awardTitle, price);
                transactions.add(transaction);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return transactions;
    }


    @Override
    public List getAllElements() {
        transactions = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Transactions\".id\n" +
                    "     , user_id\n" +
                    "     , (CONCAT(student.name, ' ', student.surname)) AS buyer\n" +
                    "     , created_at\n" +
                    "     , award_id\n" +
                    "     , aw.title\n" +
                    "     , \"Transactions\".price\n" +
                    "    FROM \"Transactions\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 3\n" +
                    "    ) student\n" +
                    "ON \"Transactions\".user_id = student.id\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM \"Awards\"\n" +
                    "        ) aw\n" +
                    "ON \"Transactions\".award_id = aw.id\n" +
                    "ORDER BY \"Transactions\".id;");
            while (rs.next()) {
                int transactionId = rs.getInt("id");
                int studentId = rs.getInt("user_id");
                String studentNameAndSurname = rs.getString("buyer");
                Timestamp dataCreation = rs.getTimestamp("created_at");
                int awardId = rs.getInt("award_id");
                String awardTitle = rs.getString("title");
                int price = rs.getInt("price");

                Transaction transaction = new Transaction(transactionId, studentId, studentNameAndSurname, dataCreation, awardId, awardTitle, price);
                transactions.add(transaction);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean insert(Object o) {
        Transaction transaction = (Transaction) o;
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("INSERT INTO \"Transactions\" "
                    + "(user_id, award_id, price, created_at) "
                    + "VALUES (?, ?, ?, ?);");

            ps.setInt(1, transaction.getBuyerId());
            ps.setInt(2, transaction.getAwardId());
            ps.setInt(3, transaction.getPrice());
            ps.setTimestamp(4, transaction.getDate());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement ps = null;

        try {
            ps = conFactory.getConnection().prepareStatement("DELETE FROM \"Transactions\" WHERE id =" + id + ";");
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
