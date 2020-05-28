package com.company.controllers;

import com.company.models.users.User;

public class AdminController {

    public AdminController(User user) {
        System.out.println("Admin Controller");
    }

    public void init() {
        System.out.println("Admin Controller init");
    }
}
