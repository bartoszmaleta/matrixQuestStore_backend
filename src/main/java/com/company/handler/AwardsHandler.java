package com.company.handler;

import com.company.dao.AwardDao;
import com.company.dao.AwardDaoDb;
import com.company.model.Award;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class AwardsHandler implements HttpHandler {

    private AwardDao awardDao = new AwardDaoDb();


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        try {
            List<Award> awards = this.awardDao.getAllElements();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.writeValueAsString(awards);

            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
            exchange.sendResponseHeaders(200, response.length());


        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

