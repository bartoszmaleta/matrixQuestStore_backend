package com.company.handler;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8003), 0);

        server.createContext("/register", new RegisterHandler());
        server.createContext("/users", new UsersHandler());
        server.createContext("/mentors", new MentorHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}
