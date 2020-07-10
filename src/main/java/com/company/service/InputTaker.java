package com.company.service;

import java.util.Scanner;

public class InputTaker {
    public static Scanner scanner = new Scanner(System.in);

    public static int takeIntInputWithMessage(String message) {
//        scanner.nextLine();
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

    public static String takeStringInputWithMessageForFirstInput(String message) {
        scanner.nextLine();     // don't know why!
        System.out.println(message);
        return scanner.nextLine();
    }

    public static String takeStringInputWithoutMessage() {
        return scanner.nextLine();
    }
}
