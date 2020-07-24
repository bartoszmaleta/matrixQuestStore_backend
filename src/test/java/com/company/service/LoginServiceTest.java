package com.company.service;

import com.company.dao.*;
import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
    private static UserDaoDb userDaoDb;
    private static LoginService loginService;

    @BeforeAll
    static void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
                , "org.postgresql.Driver"
                , "pirqathgcgzhbg"
                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
        );
        userDaoDb = Mockito.mock(UserDaoDb.class);
        loginService = new LoginService(connectionFactory);
    }

    @Test
    public void should_returnUser_when_emailAndPasswordProvided() {
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
        assertEquals(userDaoDb
                .readUserByEmailAndPassword(user.getEmail(), user.getPassword()), loginService.readUserWithEmailAndPassword
                ("cristiano@gmail", "pass"));
    }
}