package com.company.handler;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class StudentHandler implements HttpHandler {
    private UserDao userDao = new UserDaoDb();
    private ObjectMapper mapper;

    public StudentHandler() {
        this.userDao = new UserDaoDb();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        try {
            List<User> students = this.userDao.getStudents();
            response = this.mapper.writeValueAsString(students);

            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
            exchange.sendResponseHeaders(200, response.length());


        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}