package com.company;

import com.company.dao.AwardDaoDb;
import com.company.dao.UserDaoDb;
import com.company.models.users.Role;
import com.company.models.users.Student;
import com.company.models.users.User;

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
        AwardDaoDb awardDAO= new AwardDaoDb();
////        awardDAO.readAllAwards();
////        awardDAO.readAllAwardsOrderByData();
////        awardDAO.readAllAwardsOrderById();
//        awardDAO.readAllAwardsOrderByPrice("ASC");
//        Date date = new Date();
//        Award awardToAdd = new Award(10, "Test title", "Test description", 45, "img-src", new Timestamp(date.getTime()), 3);
//        awardDAO.addAward(awardToAdd);
        UserDaoDb userDaoDb = new UserDaoDb();
//        User student = new Student("student320", "student123", "student@wp.pl", Role.STUDENT, "Student", "Studentowski", 1);
////        userDaoDb.addUserToDatabase(student);
//        userDaoDb.deleteUserById(15);
//        userDaoDb.readUsers();


//        MentorController mentorController = new MentorController();
//        mentorController.init();
//        userDaoDb.editUserNameById(3, "Krzysztof");

    }
}
