package com.company;

import com.company.controllers.LoggingController;
import com.company.dao.AwardDAO;
import com.company.models.Award;

import java.io.FileNotFoundException;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws FileNotFoundException {

//        LoggingController loginController = new LoggingController();
//        loginController.init();
////        System.out.println( "Hello World!" );
        AwardDAO awardDAO= new AwardDAO();
////        awardDAO.readAllAwards();
////        awardDAO.readAllAwardsOrderByData();
////        awardDAO.readAllAwardsOrderById();
//        awardDAO.readAllAwardsOrderByPrice("ASC");
        Date date = new Date();
        Award awardToAdd = new Award(10, "Test title", "Test description", 45, "img-src", new Timestamp(date.getTime()), 3);
        awardDAO.addAward(awardToAdd);
    }
}
