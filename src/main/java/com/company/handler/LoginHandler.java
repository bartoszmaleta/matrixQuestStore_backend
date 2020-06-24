package com.company.handler;

import com.company.controllers.LoggingController;
import com.company.helpers.HttpResponses;
import com.company.models.users.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.net.http.HttpResponse;

public class LoginHandler implements HttpHandler {
    private LoggingController loggingController;
    private HttpResponses httpResponses;

    public LoginHandler(LoggingController loggingController, HttpResponse httpResponse) {
        this.loggingController = loggingController;
        this.httpResponses = httpResponses;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        if (method.equals("POST") && (uri.toString().equals("/login"))) {
            loginUser(exchange);
        } // TODO: cookie
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
        System.out.println("loginData before split: ");
//        loginData = loginData.substring(1, loginData.length() - 1);

        String[] emailAndPassword = loginData.split(",");
        User user = loggingController.login(emailAndPassword[0], emailAndPassword[1]);
        return user;
    }

    private String getUserClassName(User user) {
        return user.getClass().getSimpleName();
    }
}
