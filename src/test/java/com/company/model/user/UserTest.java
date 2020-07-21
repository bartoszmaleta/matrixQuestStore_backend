package com.company.model.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserTest {

    @Test
    public void should_throwException_when_emailIsNotProvided() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                User nUser = new Student("name", "surname", "login", "pass", "", 3, "../resources/avatars/loginLogo.jpg");
        });
    }

}