package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    LoginService loginService = new LoginService();
    UserDaoDb userDaoDb = Mockito.mock(UserDaoDb.class);

    @Test
    public void testGetUser() {
        User user = new Student()
                .setId(1)
                .setName("Cristiano")
                .setSurname("Ronaldo")
                .setLogin("cr7")
                .setPassword("pass")
                .setEmail("cristiano@gmail")
                .setAvatarSource("../resoruce/avatars/cristiano@gmail.com_logo")
                .setRoleEnum(3);

        Mockito.when(userDaoDb
                .readUserByEmailAndPassword(user.getEmail(), user.getPassword()))
                .thenReturn(user);
        assertEquals(user, loginService.readUserWithEmailAndPassword
                ("cristiano@gmail", "pass"));
    }
}