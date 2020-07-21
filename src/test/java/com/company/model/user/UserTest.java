package com.company.model.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertEquals("User", newUser.getSurname());
        assertEquals("addUser", newUser.getLogin());
        assertEquals("addedUser@", newUser.getEmail());
        assertEquals(Role.STUDENT, newUser.getRole());
    }

//    @ParameterizedTest
//    @ValueSource(numbers = {1, 2, 3})
    @Test
    public void should_setSuitableRole_when_providedUserRoleId() {
        User newUser = new Student("name", "surname", "login", "pass", "newEmail@gmail.com", 3, "../resources/avatars/loginLogo.jpg");
        User newUser2 = new Mentor("name", "surname", "login", "pass", "newEmail@gmail.com", 2, "../resources/avatars/loginLogo.jpg");
        User newUser3 = new Admin("name", "surname", "login", "pass", "newEmail@gmail.com", 1, "../resources/avatars/loginLogo.jpg");

        assertEquals(Role.STUDENT, newUser.getRole());
        assertEquals(Role.MENTOR, newUser2.getRole());
        assertEquals(Role.ADMIN, newUser3.getRole());
    }

    @Test
    public void should_throwException_when_providedWrongUserRoleId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User newUser3 = new Admin("name", "surname", "login", "pass", "newEmail@gmail.com", -1, "../resources/avatars/loginLogo.jpg");
        });
//        assertEquals(Role.ADMIN, newUser3.getRole());
    }

}