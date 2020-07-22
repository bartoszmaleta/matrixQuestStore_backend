package com.company.dao.DaoConnection;

import com.company.dao.*;

public class HerokuDaoFactory extends DaoFactory {

    public final String DATABASE_URL = "jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/dcmgt3tfcp4n6o";
    public final String JDBC_DRIVER = "org.postgresql.Driver";
    public final String USER_LOGIN = "tilcavmrsuhbzj";
    public final String USER_PASSWORD = "37e3925b366710ece9a679ad72d401e74bc6bb4ed1239676aaffef00ed27fc52";


    @Override
    public AwardDao getAwardDao() {
        return new AwardDaoDb();
    }

    @Override
    public QuestDao getQuestDao() {
        return new QuestDaoDb();
    }

    @Override
    public StatisticsDao getStatisticsDao() {
        return new StatisticsDaoDb();
    }

    @Override
    public StudentDetailsDao getStudentsDetailsDao() {
        return new StudentDetailsDaoDb();
    }

    @Override
    public StudentsAwardsDao getStudentsAwardsDao() {
        return new StudentsAwardsDaoDb();
    }

    @Override
    public TransactionDao getTransactionDao() {
        return new TransactionDaoDb();
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoDb();
    }
}
