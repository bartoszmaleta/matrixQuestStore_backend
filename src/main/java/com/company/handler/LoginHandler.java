package com.company.handler;

import com.company.helpers.Parsers;
import com.company.model.user.User;
import com.company.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

public class LoginHandler implements HttpHandler {
    private final LoginService loginService;
    private final ObjectMapper mapper;

    public LoginHandler() {
        this.loginService = new LoginService();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "user not received";
        String method = exchange.getRequestMethod();
        if (!method.equals("POST")) {
            sendResponse(response, exchange, 200);
            return;
        }
        sendLoginResponse(exchange);
    }

    private void sendLoginResponse(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        Map<String, String> data = Parsers.parseFormData(br.readLine());

        User user = loginService.readUserFromDaoByEmailOrPassword(data.get("email"), data.get("password"));

        String response = mapper.writeValueAsString(user);
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
}
