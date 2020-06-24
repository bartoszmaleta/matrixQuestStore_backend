package com.company.handler;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class UsersHandler implements HttpHandler {
    private UserDao userDao = new UserDaoDb();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        try {
            List<User> users = this.userDao.getAllElements();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.writeValueAsString(users);

            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
            exchange.sendResponseHeaders(200, response.length());



        } catch (IOException e) {
            exchange.sendResponseHeaders(404, response.length());
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
