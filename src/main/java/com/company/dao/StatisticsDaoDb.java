package com.company.dao;

import com.company.models.statistics.AwardCountByMentor;
import com.company.models.statistics.QuestCountByMentor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDaoDb implements StatisticsDao {
//    List<QuestCountByMentor> counts;
    ConnectionFactory conFactory;

    public StatisticsDaoDb() {
        conFactory = new ConnectionFactory();
    }

    @Override
    public List getQuestCountByMentor() {
        List<QuestCountByMentor> statistics = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT\n" +
                    "    (CONCAT(u.name, ' ', u.surname)) AS mentor_credentials\n" +
                    "    , COUNT(q.id) AS amount_of_quests\n" +
                    "FROM \"Quests\" q\n" +
                    "JOIN users u\n" +
                    "ON u.id = q.mentor_id\n" +
                    "GROUP BY mentor_credentials\n" +
                    "ORDER BY amount_of_quests DESC;");

            while (rs.next()) {
                String mentorNameAndSurname = rs.getString("mentor_credentials");
                int total_quests = rs.getInt("amount_of_quests");

                QuestCountByMentor stat = new QuestCountByMentor(mentorNameAndSurname
                        , total_quests);

                statistics.add(stat);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statistics;
    }

    @Override
    public List<AwardCountByMentor> getAwardCountByMentor() {
        List<AwardCountByMentor> statistics = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT\n" +
                    "    (CONCAT(u.name, ' ', u.surname)) AS mentor_credentials\n" +
                    "    , COUNT(a.id) AS amount_of_awards\n" +
                    "FROM \"Awards\" a\n" +
                    "JOIN users u\n" +
                    "ON u.id = a.creator_id\n" +
                    "GROUP BY mentor_credentials\n" +
                    "ORDER BY amount_of_awards DESC;");

            while (rs.next()) {
                String mentorNameAndSurname = rs.getString("mentor_credentials");
                int total_awards = rs.getInt("amount_of_awards");

                AwardCountByMentor stat = new AwardCountByMentor(mentorNameAndSurname
                        , total_awards);

                statistics.add(stat);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statistics;
    }

}
