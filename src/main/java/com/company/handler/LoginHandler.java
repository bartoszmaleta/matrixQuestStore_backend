package com.company.handler;

import com.company.helpers.HttpResponses;
import com.company.model.user.User;
import com.company.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.*;

public class LoginHandler extends HandlersParent implements HttpHandler {
    private final LoginService loginService;
    private HttpResponses httpResponses;
    private final ObjectMapper mapper;

//    public LoginHandler() {
//        this.httpResponses = new HttpResponses();
//        this.loginService = new LoginService();
//        this.mapper = new ObjectMapper();
//    }

    // TODO: check if working: ????
    public LoginHandler(LoginService loginService, ObjectMapper objectMapper) {
        this.loginService = loginService;
        this.mapper = objectMapper;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "user not received";

        String method = exchange.getRequestMethod();
        if (!method.equals("POST")) {
            sendResponse(response, exchange, 404);
            return;
        }
        sendLoginResponse(exchange);
    }

    private void sendLoginResponse(HttpExchange exchange) throws IOException {
        Map<String, String> data = super.getDataMap(exchange);

        User user = this.loginService.
                readUserWithEmailAndPassword(data.get("email"), data.get("password"));
//
        String response = this.mapper.writeValueAsString(user);
        // TODO: cookie
        super.sendResponse(response, exchange, 200);
    }
}
