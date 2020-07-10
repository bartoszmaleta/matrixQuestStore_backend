package com.company.handler;

import com.company.controller.LoggingController;
import com.company.helpers.HttpResponses;
import com.company.helpers.Parsers;
import com.company.model.user.User;
import com.company.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.*;

public class LoginHandler implements HttpHandler {
    private final LoggingController loggingController; // NOT USED
    private final LoginService loginService;
    private final HttpResponses httpResponses;
    private final ObjectMapper mapper;

    public LoginHandler() {
        this.loggingController = new LoggingController();
        this.httpResponses = new HttpResponses();
        this.loginService = new LoginService();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "user not received";

        String method = exchange.getRequestMethod();
        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);

            Map<String, String> data = Parsers.parseFormData(br.readLine());
            User user = this.loginService.
                    readUserFromDaoByEmailOrPassword(data.get("email"), data.get("password"));
//
            response = this.mapper.writeValueAsString(user);
        } // TODO: cookie
        sendResponse(response, exchange, 200);
    }

    private void sendResponse(String response, HttpExchange httpExchange, int status) throws IOException {
        if (status == 200) {
            httpExchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            httpExchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        }

        httpExchange.sendResponseHeaders(status, response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        os.write((response.getBytes()));
        os.close();
    }




    private void loginUser(HttpExchange exchange) {
        User user = null;
        try {
            user = getUserData(exchange.getRequestBody());
            httpResponses.sendResponse200(exchange, getUserClassName(user));
            // TODO: cookie
        } catch (Exception e) {
            System.out.println("No user in db");
            e.printStackTrace();
        }
    }

    private User getUserData(InputStream requestBody) throws IOException {
        InputStreamReader isr = new InputStreamReader(requestBody, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String loginData = br.readLine();

        String[] emailAndPassword = loginData.split(",");

        User user = loggingController.login(emailAndPassword[0], emailAndPassword[1]);
        return user;
    }

    private String getUserClassName(User user) {
        return user.getClass().getSimpleName();
    }
}
