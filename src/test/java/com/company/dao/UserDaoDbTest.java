package com.company.dao;

import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoDbTest {
    ConnectionFactory connectionFactory = new ConnectionFactory(
            "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com/da8tt4mh63b7nc"
            , "org.postgresql.Driver"
            , "pirqathgcgzhbg"
            , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
    );
    UserDao userDao = new UserDaoDb(connectionFactory);


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

        assertEquals(cristiano, this.userDao.getById(29));
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
        assertTrue(this.userDao.insert(newUser));

//        this.userDao.insert(newUser);
//        assertEquals(newUser, this.userDao.readUserByEmailAndPassword("addedUser@", "pass"));
    }


}