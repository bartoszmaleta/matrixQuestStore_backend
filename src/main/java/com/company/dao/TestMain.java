package com.company.dao;

import com.company.controllers.LoggingController;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException, FileNotFoundException {

//        View.viewAllStudents();
//        View.viewAllQuests();
//        View.viewAllQuestsWithMentors();
//        View.viewAllAwards();
//        View.viewAllAwardsWithMentors();
//
        LoggingController loggingController = new LoggingController();
        loggingController.init();
    }
}


// TODO: date in quest!!
// TODO: edit generic!

// TODO: WHERE inputs?? AdminController or in adminService???


// TODO: While loops before inputs!!!

// TODO user_detail??