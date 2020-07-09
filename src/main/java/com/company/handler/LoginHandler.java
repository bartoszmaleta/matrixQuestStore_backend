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
    private LoggingController loggingController; // NOT USED
    private LoginService loginService;
    private HttpResponses httpResponses;
    private ObjectMapper mapper;

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
        // DIFFERENT OPTION - NOT WORKS RIGHT NOW
//        URI uri = exchange.getRequestURI();
//        if (method.equals("POST") && (uri.toString().equals("/login"))) {
        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);

            Map<String, String> data = Parsers.parseFormData(br.readLine());
            User user = this.loginService.
                    readUserFromDaoByEmailOrPassword(data.get("email"), data.get("password"));
//
            System.out.println("Before response = " + response);
            response = this.mapper.writeValueAsString(user);

            System.out.println("After response, mapperAsString = " + response);

//
//            List<User> userAsList = new ArrayList<>();
//            userAsList.add(user);
//            System.out.println("List = " + userAsList);
//            response = mapper.writeValueAsString(userAsList);
//            System.out.println("mapper = " + response);

            // ALL USERS
//            UserDao userDao = new UserDaoDb();
//            List<User> users = userDao.getAllElements();
//            System.out.println("Before response = " + response);
//            response = mapper.writeValueAsString(users);
//            System.out.println("After response = " + response);

//            response = "user received";

            // DIFFERENT OPTION - NOT WORKS RIGHT NOW
//            loginUser(exchange);
        } // TODO: cookie

        System.out.println("Before sendReponse()");
        sendResponse(response, exchange, 200);

    }

    private void sendResponse(String response, HttpExchange httpExchange, int status) throws IOException {
        if (status == 200) {
            httpExchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            httpExchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
//            httpExchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("true"));
        }

        httpExchange.sendResponseHeaders(status, response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        System.out.println(os);
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
        System.out.println("loginData before split: ");
//        loginData = loginData.substring(1, loginData.length() - 1);

        String[] emailAndPassword = loginData.split(",");

        System.out.println("emailAndPassword Array = " + Arrays.toString(emailAndPassword));
        User user = loggingController.login(emailAndPassword[0], emailAndPassword[1]);
        return user;
    }

    private String getUserClassName(User user) {
        return user.getClass().getSimpleName();
    }
}
