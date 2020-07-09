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
import java.nio.charset.StandardCharsets;
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
        System.out.println("action = " + action);

        String response = "";
        String method = httpExchange.getRequestMethod(); // POST or GET

        System.out.println("array methods = " + Arrays.toString(actions));
        System.out.println(httpExchange.getResponseHeaders().toString());
        System.out.println("method = " + method);

        try {
            if (method.equals("GET")) {

                switch (action) {
                    case "details":
                        //np. http://localhost:8003/mentors/details/2
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
                InputStreamReader isr;
                BufferedReader br;
                Map<String, String> data;

                Award award;
                Date date;
                int mentorsId;

                List<Award> awards;

                switch (action) {
                    case "addAward":
                        System.out.println("I am here - addAward POST");
                        //http:localhost:8003/mentors/addAward
                        // and mentorId in body!

                        isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                        br = new BufferedReader(isr);

                        data = Parsers.parseFormData(br.readLine());
                        System.out.println(data);
                        System.out.println(data.get("description"));
                        System.out.println(data.get("title"));

                        award = new Award();

                        date = new Date();

                        mentorsId = Integer.parseInt(data.get("mentorsId"));
                        System.out.println(mentorsId);

                        award.setTitle(data.get("title"))
                                .setImageSrc(data.get("imageSrc"))
                                .setDescription(data.get("description"))
                                .setPrice(Integer.parseInt(data.get("price")))
                                .setMentorId(mentorsId)
                                .setDataCreation(new Timestamp(date.getTime()));

                        System.out.println("toString Award = " + award.toString());

                        this.mentorService.addAwardToDatabase(award);

                        awards = this.mentorService.getAllAwardsOfThisMentorByUserId(mentorsId);
                        response = mapper.writeValueAsString(awards);
                        System.out.println("Add award - confirmed");
                        break;
                    case "deleteAward":
                        System.out.println("I am here - deleteAward, but POST method ");
                        //http:localhost:8003/mentors/deleteAward
                        // and mentorId in body!

                        isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
                        br = new BufferedReader(isr);
                        data = Parsers.parseFormData(br.readLine());
                        System.out.println("data = " + data);
                        System.out.println("data = " + data.get("id"));

                        award = new Award();
                        date = new Date();
                        int cardIdToDelete;     // TODO: move upwards!!!

                        cardIdToDelete = Integer.parseInt(data.get("id")); // TODO: in frontend
                        System.out.println(cardIdToDelete);
                        mentorsId = Integer.parseInt(data.get("mentorsId"));
                        System.out.println("mentorsId = " + mentorsId);

                        this.mentorService.deleteAwardById(cardIdToDelete);
                        response = "award DELETE - done!";
                }

            } else if (method.equals("DELETE")) {
                // TODO: can't get here, because i get 'OPTIONS" method, despite sending DELETE
                System.out.println("i am in DELETE");
                InputStreamReader isr;
                BufferedReader br;
                Map<String, String> data;

                Award award;
                Date date;

                int cardIdToDelete;

                int mentorsId;


                List<Award> awards;

                switch (action) {
                    case "deleteAward":
                        System.out.println("I am here - delete DELETE");
                        //http:localhost:8003/mentors/deleteAward
                        // and mentorId in body!

                        isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
                        br = new BufferedReader(isr);
                        data = Parsers.parseFormData(br.readLine());
                        System.out.println("data = " + data);
                        System.out.println("data = " + data.get("cardIdToDelete"));

                        award = new Award();
                        date = new Date();

                        cardIdToDelete = Integer.parseInt(data.get("cardIdToDelete")); // TODO: in frontend
                        mentorsId = Integer.parseInt(data.get("mentorsId"));
                        System.out.println(mentorsId);

                        this.mentorService.deleteAwardById(cardIdToDelete);
                        response = "award DELETE - done!";
//                         List<Award> awards = this.mentorService.getAllAwardsOfThisMentorByUserId(mentorsId);
//                         response = this.mapper.writeValueAsString(awards);

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
