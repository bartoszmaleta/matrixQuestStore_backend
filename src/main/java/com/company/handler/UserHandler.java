package com.company.handler;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.User;
import com.company.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserHandler implements HttpHandler {
    private final UserDao userDao;
    private final AdminService adminService;
    private final ObjectMapper mapper;

    public UserHandler() {
        this.userDao = new UserDaoDb();
        this.adminService = new AdminService();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String url = httpExchange.getRequestURI().getRawPath();
        String[] actions = url.split("/");
        String action = (actions.length == 2) ? "" : (actions[2].matches("\\d+") ? "details" : actions[2]);
        String response = "";

        try {
            switch (action) {
                case "add":
                    // TODO:
                    break;
                case "details":
                    // http://localhost:8003/users/details/1
                    User user = this.adminService.readUserFromDaoById(Integer.parseInt(actions[3]));
                    response = this.mapper.writeValueAsString(user);
                    break;
                default:
                    // http://localhost:8003/users
                    List<User> users = this.userDao.getAllElements();
                    response = this.mapper.writeValueAsString(users);
            }
            sendResponse(response, httpExchange, 200);
        } catch (Exception e) {
            sendResponse(response, httpExchange, 404);
        }
    }

    private void sendResponse(String response, HttpExchange httpExchange, int status) throws IOException {
        if (status == 200) {
            httpExchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            httpExchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        }

        httpExchange.sendResponseHeaders(status, response.length());

        OutputStream os = httpExchange.getResponseBody();
        os.write((response.getBytes()));
        os.close();
    }
}
