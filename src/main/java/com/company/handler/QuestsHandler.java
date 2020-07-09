package com.company.handler;

import com.company.dao.QuestDao;
import com.company.dao.QuestDaoDb;
import com.company.model.Award;
import com.company.model.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class QuestsHandler implements HttpHandler {

    private QuestDao questDao;
    private ObjectMapper mapper;


    public QuestsHandler() {
        this.questDao = new QuestDaoDb();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        try {
            List<Quest> quests = this.questDao.getAllElements();
            response = this.mapper.writeValueAsString(quests);

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
