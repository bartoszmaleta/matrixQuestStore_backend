package com.company.service;

import java.util.Scanner;

public class InputTaker {
    public static Scanner scanner = new Scanner(System.in);

    public static int takeIntInputWithMessage(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
    public static int takeIntInputWithoutMessage() {
        return scanner.nextInt();
    }

    public static String takeStringInputWithMessage(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static String takeStringInputWithoutMessage() {
        return scanner.nextLine();
    }
}
