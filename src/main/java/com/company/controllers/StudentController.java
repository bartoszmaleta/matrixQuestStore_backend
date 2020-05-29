package com.company.controllers;

import com.company.models.users.User;

public class StudentController {
    private User user;

    public StudentController(User user) {
        this.user = user;

        System.out.println("Student Controller");
        System.out.println("Hello, Your email: " + this.user.getEmail());
    }

    public void init() {
        System.out.println("Student Controller init");
    }
}
