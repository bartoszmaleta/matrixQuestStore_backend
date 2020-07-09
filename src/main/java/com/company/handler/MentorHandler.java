package com.company.handler;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.helpers.Parsers;
import com.company.model.Award;
import com.company.model.Quest;
import com.company.model.statistics.QuestCountByMentor;
import com.company.model.user.User;
import com.company.service.MentorService;
import com.company.service.StatisticsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class MentorHandler implements HttpHandler {
    private MentorService mentorService;
    private StatisticsService statisticsService;
    private ObjectMapper mapper;

    public MentorHandler() {
        this.mentorService = new MentorService();
        this.statisticsService = new StatisticsService();
        mapper = new ObjectMapper();

        // TODO: move to another handler
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String url = httpExchange.getRequestURI().getRawPath();
        String[] actions = url.split("/");
        String action = (actions.length == 2) ? "" : (actions[2].matches("\\d+") ? "details" : actions[2]);
        String response = "";
        String method = httpExchange.getRequestMethod(); // POST or GET

        System.out.println("array methods = " + Arrays.toString(actions));

        try {
            if (method.equals("GET")) {

                switch (action) {
//                case "add":
//                    // TODO:
//                    break;
                    case "details":
                        //np. http://localhost:8003/users/details/1
                        User user = this.mentorService.readUserFromDaoById(Integer.parseInt(actions[3]));
                        response = this.mapper.writeValueAsString(user);
                        break;
                    case "myQuests":
//                    http://localhost:8003/mentors/myQuests/2  <--- mentorId
                        int idOfMentor = Integer.parseInt(actions[3]);
                        System.out.println("id of mentor = " + idOfMentor);
                        List<Quest> quests = this.mentorService.getAllQuestsOfThisMentorByUserId(idOfMentor);
                        System.out.println(quests);
                        response = this.mapper.writeValueAsString(quests);
                        System.out.println(response);
                        break;
                    case "questsByMentor": // TODO: move to another handler
                        List<QuestCountByMentor> questsCount = this.statisticsService.getQuestsCountByMentor();
                        response = this.mapper.writeValueAsString(questsCount);
                        System.out.println(response);
                        break;
                    default:
                        //np. http://localhost:8003/mentors
                        //np. http://localhost:8003/mentorsqweqwe
                        System.out.println("/mentors METHOD GET");
                        List<User> users = this.mentorService.getAllMentors();
                        response = this.mapper.writeValueAsString(users);
                }
            } else if (method.equals("POST")) {
                switch (action) {
                    case "addAward":
                        System.out.println("I am here - addAward POST");
                        //http:localhost:8003/mentors/addAward
                        // and mentorId in body!

                        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                        BufferedReader br = new BufferedReader(isr);

                        Map<String, String> data = Parsers.parseFormData(br.readLine());
                        System.out.println(data);
                        System.out.println(data.get("description"));
                        System.out.println(data.get("title"));

                        Award award = new Award();

                        Date date = new Date();

                        int mentorsId = Integer.parseInt(data.get("mentorsId"));
                        System.out.println(mentorsId);

                        award.setTitle(data.get("title"))
                                .setImageSrc(data.get("imageSrc"))
                                .setDescription(data.get("description"))
                                .setPrice(Integer.parseInt(data.get("price")))
                                .setMentorId(mentorsId)
                                .setDataCreation(new Timestamp(date.getTime()));

                        System.out.println("toString Award = " + award.toString());

                        this.mentorService.addAwardToDatabase(award);

                        List<Award> awards = this.mentorService.getAllAwardsOfThisMentorByUserId(mentorsId);
                        response = this.mapper.writeValueAsString(awards);

                    default:
                        //np. http://localhost:8003/mentors
                        //np. http://localhost:8003/mentorsqweqwe
                        System.out.println("/mentors METHOD POST ]");
                        List<User> users = this.mentorService.getAllMentors();
                        response = this.mapper.writeValueAsString(users);
                }
            }
        } catch (Exception e) {
            sendResponse(response, httpExchange, 404);
        }
        sendResponse(response, httpExchange, 200);


//        httpExchange.sendResponseHeaders(200, response.length());
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
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
