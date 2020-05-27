package com.company;

import com.company.controllers.LoggingController;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws FileNotFoundException {

        LoggingController loginController = new LoggingController();
        loginController.init();
//        System.out.println( "Hello World!" );
    }
}
