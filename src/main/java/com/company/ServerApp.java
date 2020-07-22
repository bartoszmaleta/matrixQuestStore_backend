package com.company;

import com.company.dao.ConnectionFactory;
import com.company.dao.DaoConnection.ConnectionCredentials;
import com.company.dao.UserDaoDb;
import com.company.handler.UserHandler;
import com.company.handler.*;
import com.company.helpers.HttpResponses;
import com.company.service.LoginService;
import com.company.service.MentorService;
import com.company.service.StatisticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8003), 0);

        server.createContext("/users", new UserHandler());
        server.createContext("/register", new RegisterHandler());
//        server.createContext("/users", new AdminHandler());
//        server.createContext("/mentors", new MentorHandler());

        // TODO: check which one is working??????
        server.createContext("/mentors", new MentorHandler(new MentorService(), new StatisticsService(), new ObjectMapper()));
        server.createContext("/students", new StudentHandler());
//        server.createContext("/login", new LoginHandler());

        // TODO: check if working: ????? // TODO: probably yes!!!
        server.createContext("/login", new LoginHandler(new LoginService(), new ObjectMapper()));



//        server.createContext("/login", new LoginHandler(new LoginService(new UserDaoDb(new ConnectionFactory(ConnectionCredentials))), new ObjectMapper()));



        server.createContext("/awards", new AwardsHandler());
        server.createContext("/quests", new QuestsHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}
