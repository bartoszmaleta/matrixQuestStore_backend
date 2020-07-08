package com.company;

import com.company.handler.UserHandler;
import com.company.handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8003), 0);

        server.createContext("/users", new UserHandler());
        server.createContext("/register", new RegisterHandler());
//        server.createContext("/users", new AdminHandler());
        server.createContext("/mentors", new MentorHandler());
        server.createContext("/students", new StudentHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/awards", new AwardsHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}
