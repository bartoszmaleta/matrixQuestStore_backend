package com.company.service;

import com.company.view.StudentView;

public class StudentService {
    private StudentView studentView;

    public StudentService() {
        this.studentView = new StudentView();
    }

    public void displayMenu() {
        studentView.showMenu();
    }
}
