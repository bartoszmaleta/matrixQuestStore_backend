package com.company.controllers;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class UserController implements HttpHandler {
    private UserDao userDao;
    private LoggingController loggingController;
    public UserController() {
        this.userDao = new UserDaoDb();
        this.loggingController = new LoggingController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String url = exchange.getRequestURI().getRawPath();
        String[] methods = url.split("/");

        System.out.println("array methods = " + Arrays.toString(methods));
        System.out.println("methods[2] = " + methods[2]);

        String response = "ok";

        String numberPattern = "\\d+";
        try {
            if (methods[2].matches(numberPattern)) {
                User user = this.userDao.readUserById(Integer.parseInt(methods[2]));
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.writeValueAsString(user);
            } else if (methods[2].equals("add")) {
                // TODO add new user -> POST
            } else {
                List<User> users = this.userDao.getAllElements();
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.writeValueAsString(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());

        os.close();
    }
}
