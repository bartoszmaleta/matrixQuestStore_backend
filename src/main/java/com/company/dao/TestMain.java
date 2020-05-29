package com.company.dao;

import com.company.controllers.LoggingController;
import com.company.models.users.User;
import com.company.view.View;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException, FileNotFoundException {

//        View.viewAllStudents();
//        View.viewAllQuests();
        View.viewAllQuestsWithMentors();
//        View.viewAllAwards();

//        LoggingController loggingController = new LoggingController();
//        loggingController.init();
    }
}
