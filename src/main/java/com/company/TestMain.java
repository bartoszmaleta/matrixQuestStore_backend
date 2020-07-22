package com.company;

import com.company.dao.ConnectionFactory;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;

public class TestMain {


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.getConnection();

        UserDao userDao = new UserDaoDb();
//        System.out.println(userDao.getById(18));
//        System.out.println(userDao.getStudents());
        System.out.println(userDao.readUserIdByEmail("linux@gmail"));
        System.out.println(userDao.readUserByEmailAndPassword("linux@gmail", "pass"));



    }
}
