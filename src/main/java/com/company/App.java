package com.company;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    PostgreSQLJDBC pc = new PostgreSQLJDBC();
    DAO dao = new DAO();
    pc.Connect();
    dao.Table();
    }
}
