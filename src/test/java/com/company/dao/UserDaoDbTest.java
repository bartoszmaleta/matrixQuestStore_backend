package com.company.dao;

import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoDbTest {
    private static UserDaoDb userDao;

    @BeforeAll
    static void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
                , "org.postgresql.Driver"
                , "pirqathgcgzhbg"
                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
        );
        System.out.println("qasdasdsad");
        userDao = new UserDaoDb(connectionFactory);
    }

    @Test
    public void should_returnUser_when_idIsProvidedId() {
        User cristiano = new Student()
                .setId(29)
                .setName("Cristiano")
                .setSurname("Ronaldo")
                .setLogin("cr7")
                .setPassword("pass")
                .setEmail("cristiano@")
                .setRoleEnum(3);

        assertEquals(cristiano, userDao.getById(29));
    }

    @Test
    public void should_returnTrue_when_userIsAdded() {
        User newUser = new Student()
                .setName("Added")
                .setSurname("User")
                .setLogin("addUser")
                .setPassword("pass")
                .setEmail("addedUser@")
                .setRoleEnum(3);
        assertTrue(userDao.insert(newUser));
    }

    @Test
    public void should_returnFalse_when_userWithWrongIdIsDeleted() {
        int idUser = -1;
        assertFalse(userDao.delete(idUser));
    }

    @Disabled("After test db won't recover, user will still be deleted!")
    @Test
    public void should_returnTrue_when_userWithIdIsDeleted() {
        int idUser = 27;
        assertTrue(userDao.delete(idUser));
    }

    @Test
    public void should_returnFalse_when_studentWithWrongIdIsDeleted() {
        int idUser = -1;
        assertFalse(userDao.deleteStudent(idUser));
    }

    @Disabled("After test db won't recover, user will still be deleted!")
    @Test
    public void should_returnTrue_when_studentWithIdIsDeleted() {
        int idUser = 27;
        assertTrue(userDao.deleteStudent(idUser));
    }

    @Disabled("There is no Student_Details table in backup database.")
    @Test
    public void should_returnFalse_when_studentDetailsWithWrongIdIsDeleted() {
        int idUser = -1;
        assertFalse(userDao.deleteStudentDetails(idUser));
    }

    @Disabled("There is no Student_Details table in backup database.")
    @Test
    public void should_returnTrue_when_studentDetailsWithIdIsDeleted() {
        int idUser = 27;
        assertTrue(userDao.deleteStudentDetails(idUser));
    }

    @Test
    public void should_returnUserId_when_userEmailIsProvided() {
        String userEmail = "tesla@gmail";
        int userId = 20;
        int idUserFromDao = userDao.readUserIdByEmail(userEmail);
        System.out.println("id from db = " + idUserFromDao);
        assertEquals(userId, idUserFromDao);
    }
}