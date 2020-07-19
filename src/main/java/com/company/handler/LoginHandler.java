package com.company.handler;

import com.company.helpers.HttpResponses;
import com.company.helpers.Parsers;
import com.company.model.user.User;
import com.company.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LoginHandler implements HttpHandler {
    private final LoginService loginService;
    private final HttpResponses httpResponses;
    private final ObjectMapper mapper;

    public LoginHandler() {
        this.httpResponses = new HttpResponses();
        this.loginService = new LoginService();
        this.mapper = new ObjectMapper();
    }

    // TODO: check if working: ????
    public LoginHandler(HttpResponses httpResponses, LoginService loginService, ObjectMapper objectMapper) {
        this.httpResponses = httpResponses;
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
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        Map<String, String> data = Parsers.parseFormData(br.readLine());

        User user = this.loginService.
                readUserWithEmailAndPassword(data.get("email"), data.get("password"));
//
        String response = this.mapper.writeValueAsString(user);
        // TODO: cookie
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

    private String getUserClassName(User user) {
        return user.getClass().getSimpleName();
    }
}
