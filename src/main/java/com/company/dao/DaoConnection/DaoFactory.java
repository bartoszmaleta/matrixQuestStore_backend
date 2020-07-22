package com.company.dao.DaoConnection;

import com.company.dao.*;

// Abstract class DAO Factory
public abstract class DaoFactory {

    // List of DAO types supported by the factory
    public static final int HEROKU_DATABASE = 1;
    public static final int ORACLE = 2;
    public static final int SYBASE = 3;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract AwardDao getAwardDao();
    public abstract QuestDao getQuestDao();
    public abstract StatisticsDao getStatisticsDao();
    public abstract StudentDetailsDao getStudentsDetailsDao();
    public abstract StudentsAwardsDao getStudentsAwardsDao();
    public abstract TransactionDao getTransactionDao();
    public abstract UserDao getUserDao();

    public static DaoFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case HEROKU_DATABASE:
                return new HerokuDaoFactory();
            case ORACLE    :
//                return new OracleDAOFactory();
            case SYBASE    :
//                return new SybaseDAOFactory();
            default           :
                return null;
        }
    }
}