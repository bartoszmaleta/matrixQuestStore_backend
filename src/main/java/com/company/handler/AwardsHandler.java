package com.company.handler;

import com.company.dao.AwardDao;
import com.company.dao.AwardDaoDb;
import com.company.helpers.Parsers;
import com.company.model.Award;
import com.company.model.user.Role;
import com.company.model.user.Student;
import com.company.model.user.User;
import com.company.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AwardsHandler implements HttpHandler {

    private final AwardDao awardDao;
    private final ObjectMapper mapper;
    private final AdminService adminService;

    public AwardsHandler() {
        this.awardDao = new AwardDaoDb();
        this.mapper = new ObjectMapper();
        this.adminService = new AdminService();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String url = exchange.getRequestURI().getRawPath();
        String[] actions = url.split("/");
        String action = (actions.length == 2) ? "" : (actions[2].matches("\\d+") ? "details" : actions[2]);
        System.out.println("action = " + action);

        String response = "";
        String method = exchange.getRequestMethod(); // POST or GET

        System.out.println("array methods = " + Arrays.toString(actions));
        System.out.println("headers = " + exchange.getResponseHeaders().toString());
        System.out.println("method = " + method);

        try {
            if (method.equals("POST")) {
                InputStreamReader isr;
                BufferedReader br;
                Map<String, String> data;

                if (actions[2].equals("buy")) {
                    //np. http://localhost:8003/awards/buy/29  <--- studentId
                    isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                    br = new BufferedReader(isr);

                    data = Parsers.parseFormData(br.readLine());

                    int studentId = Integer.parseInt(data.get("studentId"));
                    int awardId = Integer.parseInt(data.get("cardId"));
                    int awardPrice = Integer.parseInt(data.get("price"));


                    this.adminService.addAwardByAwardIdToStudentByStudentId(awardId, studentId, awardPrice);
                    response = "Data saved to DB";
                }
            } else if (method.equals("GET")) {
                switch (action) {
                    case "myAwards":
                        // TODO: or not?
                        break;
                    default:
                        List<Award> awards = this.awardDao.getAllElements();
                        response = this.mapper.writeValueAsString(awards);
                }
            }
        } catch (
                IOException e) {
            sendResponse(response, exchange, 404);
        }
        sendResponse(response, exchange, 200);
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

