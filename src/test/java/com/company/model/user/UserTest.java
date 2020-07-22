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
            new Student("name", "surname", "login", "pass", "", 3, "../resources/avatars/loginLogo.jpg");
        });
    }

    //    @ParameterizedTest
//    @ValueSource(numbers = {1, 2, 3})
    @Test
    public void should_setStudentRole_when_providedUserRoleId() {
        User newUser = new Student();
        newUser.setRoleEnum(1);
        assertSame(newUser.getRole(), Role.STUDENT);
    }

    @Test
    public void should_setMentorRole_when_providedUserRoleId() {
        User newUser = new Mentor();
        newUser.setRoleEnum(2);
        assertSame(newUser.getRole(), Role.MENTOR);
    }

    @Test
    public void should_setAdminRole_when_providedUserRoleId() {
        User newUser = new Admin();
        newUser.setRoleEnum(1);
        assertSame(newUser.getRole(), Role.ADMIN);
    }

    @Test
    public void should_throwException_when_providedWrongUserRoleId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Admin("name", "surname", "login", "pass", "newEmail@gmail.com", -1, "../resources/avatars/loginLogo.jpg");
        });
    }

//    @Test
//    public void should_createUser_when_fieldsSetByBuilder() {
//        User newUser = new Student()
//                .setName("Added")
//                .setSurname("User")
//                .setLogin("addUser")
//                .setPassword("pass")
//                .setEmail("addedUser@")
//                .setRoleEnum(3);
//
//        assertAll("Correctly create User with suitable fields",
//                () -> assertEquals("Added", newUser.getName())
//                , () -> assertEquals("User", newUser.getSurname())
//                , () -> assertEquals("addUser", newUser.getLogin())
//                , () -> assertEquals("addedUser@", newUser.getEmail())
//                , () -> assertEquals(Role.STUDENT, newUser.getRole())
//        );
//    }

}