package com.company;

import com.company.controller.LoggingController;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class    TerminalApp {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        LoggingController loggingController = new LoggingController();
        loggingController.init();
    }
}

// TODO: date in quest!!
// TODO: edit generic!

// TODO: WHERE inputs?? AdminController or in adminService???

// TODO: While loops before inputs!!!

// TODO user_detail??

// TODO: View.allQuests should be in Controllers or in service???

// TODO: coins as int field in Student model
//      - private String avatarFile
//      - backapckId
//      - classId

// TODO:
//    - Transaction class
//    - Module class

// TODO: loginService

// TODO: studentController and service

// TODO: move views to different classes

// TODO: possible to buy artifact

// TODO: views about students should be in AdminView????

// TODO: Why connections aren't closing???

// TODO: validators of email/phone/login/password

// TODO: inventory???

// TODO: remove id inserting! in AwardDaoDb

// TODO: UserDaoDb change readUser!!!!!!!!!

// TODO: hashing SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

// TODO: learn status http statuses and USE IT MORE OFTEN!!!

// TODO: change ResultSet to PrepareStatement();

// TODO: create default user which is created and then changes to Role by Admin

// TODO: make userDaoDb.List<User> getMentors() --> more universal!!

// TODO: Refactor helpers

// TODO: defend from injecting sql

// TODO: StudentDetailsDao ==>
//  public boolean setMentorForUser(int studentId, int mentorId) {