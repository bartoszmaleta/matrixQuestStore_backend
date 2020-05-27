package com.company;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        DBConnector db = new DBConnector();
        db.connectToDB();
    }
}
