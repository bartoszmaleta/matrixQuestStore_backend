package com.company.dao;

import com.company.model.user.Admin;
import com.company.model.user.Mentor;
import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        userDao.createTables();
    }

    @AfterEach
    public void cleanUpDatabase() {
        userDao.dropTables();
        userDao.createTables();
    }

    @Test
    public void should_returnUser_when_idIsProvidedId() {
        User cristiano = new Student()
                .setId(1)
                .setName("Cristiano")
                .setSurname("Ronaldo")
                .setLogin("cr7")
                .setPassword("pass")
                .setEmail("cristiano@gmail")
                .setAvatarSource("../resoruce/avatars/cristiano@gmail.com_logo")
                .setRoleEnum(3);

        assertEquals(cristiano, userDao.getById(1));
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

    //    @Disabled("After test db won't recover, user will still be deleted!")
    @Test
    public void should_returnTrue_when_userWithIdIsDeleted() {
        int idUser = 2;
        assertTrue(userDao.delete(idUser));
    }

    @Test
    public void should_returnFalse_when_studentWithWrongIdIsDeleted() {
        int idUser = -1;
        assertFalse(userDao.deleteStudent(idUser));
    }

    //    @Disabled("After test db won't recover, user will still be deleted!")
    @Test
    public void should_returnTrue_when_studentWithIdIsDeleted() {
        int idUser = 1;
        assertTrue(userDao.deleteStudent(idUser));
    }

//    @Disabled("There is no Student_Details table in backup database.")
//    @Test
//    public void should_returnFalse_when_studentDetailsWithWrongIdIsDeleted() {
//        int idUser = -1;
//        assertFalse(userDao.deleteStudentDetails(idUser));
//    }

//    @Disabled("This method should be private and BTW in different class!!")
//    @Test
//    public void should_returnTrue_when_studentDetailsWithIdIsDeleted() {
//        int idUser = 1;
//        assertTrue(userDao.deleteStudentDetails(idUser));
//    }

    @Test
    public void should_returnUserId_when_userEmailIsProvided() {
        String userEmail = "tesla@gmail";
        int userId = 2;
        int idUserFromDao = userDao.readUserIdByEmail(userEmail);
        System.out.println("id from db = " + idUserFromDao);
        assertEquals(userId, idUserFromDao);
    }

    @Test
    public void should_returnNull_when_userIdIsBelowOne() {
        int userId = Integer.MIN_VALUE;
        assertNull(userDao.getById(userId));
    }

    @Test
    public void should_returnNull_when_userIdIsNotFound() {
        int userId = Integer.MAX_VALUE;
        assertNull(userDao.getById(userId));
    }

    @Test
    public void should_returnListWithUsers() {
        User user1 = new Student(1, "Cristiano", "Ronaldo", "cr7", "pass", "cristiano@gmail", 3, "../resoruce/avatars/cristiano@gmail.com_logo");
        User user2 = new Mentor(2, "Nikola", "Tesla", "tesla", "pass", "tesla@gmail", 2, "../resoruce/avatars/tesla@gmail.com_logo");
        User user3 = new Admin(3, "Bill", "Gates", "microsoft", "pass", "microsoft@gmail", 1, "../resoruce/avatars/microsoft@gmail.com_logo");
        User user4 = new Mentor(4, "Warren", "Buffet", "berkshire", "pass", "berkshire@gmail", 2, "../resoruce/avatars/berkshire@gmail.com_logo");
        User user5 = new Student(5, "Jeff", "Bezos", "amazon", "pass", "amazon@gmail", 3, "../resoruce/avatars/amazon@gmail.com_logo");
        User user6 = new Admin(6, "Linus", "Torvalds", "linux", "pass", "linux@gmail", 1, "../resoruce/avatars/linux@gmail.com_logo_logo");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

        assertEquals(users, userDao.getAllElements());
    }


}