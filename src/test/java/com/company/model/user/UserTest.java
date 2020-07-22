package com.company.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    @Test
    public void should_throwException_when_emailIsNotProvided() {
        assertThrows(IllegalArgumentException.class, () -> new Student("name", "surname", "login",
                "pass", "", 3, "../resources/avatars/loginLogo.jpg"));
    }

    //    @ParameterizedTest
    //    @ValueSource(numbers = {1, 2, 3})
    @Test
    public void should_setSuitableRole_when_providedUserRoleId() {
        User newUser = new Student();
        newUser.setRoleEnum(1);
        assertSame(newUser.getRole(), Role.ADMIN);
    }

    @Test
    public void should_throwException_when_providedWrongUserRoleId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Admin("name", "surname", "login", "pass", "newEmail@gmail.com", -1, "../resources/avatars/loginLogo.jpg");
        });
    }

}