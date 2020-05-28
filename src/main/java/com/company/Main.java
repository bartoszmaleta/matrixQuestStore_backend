package com.company;

import com.company.controllers.LoggingController;
import com.company.dao.AwardDAO;

import java.io.FileNotFoundException;

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
//        awardDAO.readAllAwards();
//        awardDAO.readAllAwardsOrderByData();
//        awardDAO.readAllAwardsOrderById();
        awardDAO.readAllAwardsOrderByPrice("ASC");
    }
}
