package com.company.dao;

import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoDbTest {
    UserDao userDao = new UserDaoDb();

    @Test
    public void readUserById() {
        User cristiano = new Student()
                .setId(29)
                .setName("Cristiano")
                .setSurname("Ronaldo")
                .setLogin("cr7")
                .setPassword("pass")
                .setEmail("cristiano@")
                .setRoleEnum(3);

        assertEquals(cristiano, this.userDao.readUserById(29));
    }
}