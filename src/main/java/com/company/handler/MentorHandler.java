package com.company.handler;

import com.company.dao.StatisticsDaoDb;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.Quest;
import com.company.model.statistics.QuestCountByMentor;
import com.company.model.user.User;
import com.company.service.StatisticsDao;
import com.company.service.MentorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MentorHandler implements HttpHandler {
    private UserDao userDao;
    private MentorService mentorService;
    StatisticsDaoDb statisticsDaoDb;

    public MentorHandler() {
        this.userDao = new UserDaoDb();
        this.mentorService = new MentorService();
        this.statisticsDaoDb = new StatisticsDaoDb();
         // TODO: move to another handler
    }

//    @Override
    public void handle2(HttpExchange exchange) throws IOException {
        String response = "";

        try {
            List<User> mentors = this.userDao.getMentors();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.writeValueAsString(mentors);

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

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String url = httpExchange.getRequestURI().getRawPath();
        String[] actions = url.split("/");
        String action = (actions.length == 2) ? "" : (actions[2].matches("\\d+") ? "details" : actions[2]);
        ObjectMapper mapper = new ObjectMapper();
        String response = "";

//        System.out.println("methods[2] = " + actions[2]);
//        System.out.println("methods[2] = ");
        System.out.println("array methods = " + Arrays.toString(actions));

        try {
            switch (action) {
                case "add":
                    // TODO:
                    break;
                case "details":
                    //np. http://localhost:8003/users/details/1
                    User user = this.mentorService.readUserFromDaoById(Integer.parseInt(actions[3]));
                    response = mapper.writeValueAsString(user);
                    break;
                case "myQuests":
//                    http://localhost:8003/mentors/myQuests/2
                    int idOfMentor = Integer.parseInt(actions[3]);
                    System.out.println("id of mentor = " + idOfMentor);
//                    List<Quest> quests = this.mentorService.getAllAwardsOfThisMentorByUserId(idOfMentor);
                    List<Quest> quests = this.mentorService.getAllQuestsOfThisMentorByUserId(idOfMentor);
                    System.out.println(quests);
                    response = mapper.writeValueAsString(quests);
                    System.out.println(response);
                    break;
                case "questsByMentor": // TODO: move to another handler
                    List<QuestCountByMentor> questsCount = this.statisticsDaoDb.getQuestCountByMentor();
                    response = mapper.writeValueAsString(questsCount);
                    System.out.println(response);
                    break;
                default:         // TODO: which one?
//                case "all":
                    //np. http://localhost:8003/mentors
                    //np. http://localhost:8003/mentorsqweqwe
                    System.out.println("qweqwe");
                    List<User> users = this.userDao.getMentors();
                    response = mapper.writeValueAsString(users);
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
