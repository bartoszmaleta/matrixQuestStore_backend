package com.company.model.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    @Test
    public void should_throwException_when_emailIsNotProvided() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                User newUser = new Student("name", "surname", "login", "pass", "", 3, "../resources/avatars/loginLogo.jpg");
        });
    }

    @Test
    public void should_createUser_when_fieldsSetByBuilder() {
        User newUser = new Student()
                .setName("Added")
                .setSurname("User")
                .setLogin("addUser")
                .setPassword("pass")
                .setEmail("addedUser@")
                .setRoleEnum(3);
        assertEquals("Added", newUser.getName());
    }

}