package com.company.handler;

import com.company.helpers.Parsers;
import com.company.model.Award;
import com.company.model.Quest;
import com.company.model.statistics.QuestCountByMentor;
import com.company.model.user.User;
import com.company.service.MentorService;
import com.company.service.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

public class MentorHandler implements HttpHandler {
    private final MentorService mentorService;
    private final StatisticsService statisticsService;
    private final ObjectMapper mapper;

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
        String defaultAction = "";
        String action = (noActionProvided(actions)) ? defaultAction : (isGetDetailsAction(actions[2]) ? "details" : actions[2]);
        System.out.println("action = " + action);

        String action2;
//        // If else alternative
//                if (noActionProvided(actions)) action2 = defaultAction;
//        else if (isGetDetailsAction(actions[2])) action2 = "details";
//        else action2 = actions[2];


        String response = "";
        String method = httpExchange.getRequestMethod(); // POST or GET

        System.out.println("array methods = " + Arrays.toString(actions));
        System.out.println(httpExchange.getResponseHeaders().toString());
        System.out.println("method = " + method);

        try {
            switch (method) {
                case "GET":
                    response = handleActions(actions, action);
                    break;
                case "POST": {
                    InputStreamReader isr;
                    BufferedReader br;
                    Map<String, String> data;

                    Award award;
                    Quest quest;
                    Date date;
                    int mentorsId;

                    List<Award> awards;
                    List<Quest> quests;

                    switch (action) {
                        case "addAward":
                            //http:localhost:8003/mentors/addAward
                            // and mentorId in body!

                            isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
                            br = new BufferedReader(isr);

                            data = Parsers.parseFormData(br.readLine());

                            award = new Award();

                            date = new Date();

                            mentorsId = Integer.parseInt(data.get("mentorsId"));

                            award.setTitle(data.get("title"))
                                    .setImageSrc(data.get("imageSrc"))
                                    .setDescription(data.get("description"))
                                    .setPrice(Integer.parseInt(data.get("price")))
                                    .setMentorId(mentorsId)
                                    .setDataCreation(new Timestamp(date.getTime()));

                            this.mentorService.addAwardToDatabase(award);

                            awards = this.mentorService.getAllAwardsOfThisMentorByUserId(mentorsId);
                            response = mapper.writeValueAsString(awards);
                            break;
                        case "deleteAward":
                            //http:localhost:8003/mentors/deleteAward
                            // and mentorId in body!

                            isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
                            br = new BufferedReader(isr);
                            data = Parsers.parseFormData(br.readLine());

                            int cardIdToDelete;     // TODO: move upwards!!!

                            cardIdToDelete = Integer.parseInt(data.get("id")); // TODO: in frontend
                            mentorsId = Integer.parseInt(data.get("mentorsId"));

                            this.mentorService.deleteAwardById(cardIdToDelete);
                            response = "award DELETE - done!";

                        case "addQuest":
                            //http:localhost:8003/mentors/addQuest

                            isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
                            br = new BufferedReader(isr);

                            data = Parsers.parseFormData(br.readLine());

                            quest = new Quest();
                            date = new Date();

                            mentorsId = Integer.parseInt(data.get("mentorsId"));

                            quest.setTitle(data.get("title"))
                                    .setImageSrc(data.get("imageSrc"))
                                    .setDescription(data.get("description"))
                                    .setPrice(Integer.parseInt(data.get("price")))
                                    .setMentorId(mentorsId);


                            this.mentorService.addQuestToDatabase(quest);
                            quests = this.mentorService.getAllQuestsOfThisMentorByUserId(mentorsId);
                            response = mapper.writeValueAsString(quests);
                            break;

                        case "deleteQuest":
                            //http:localhost:8003/mentors/deleteQuest

                            isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
                            br = new BufferedReader(isr);
                            data = Parsers.parseFormData(br.readLine());

                            int questIdToDelete;

                            questIdToDelete = Integer.parseInt(data.get("id"));

                            int mentorId = Integer.parseInt(data.get("mentorsId"));

                            this.mentorService.deleteQuestById(questIdToDelete);
                            response = "Quest successfully deleted";


                    }

                    break;
                }
                case "DELETE": {
                    // TODO: can't get here, because i get 'OPTIONS" method, despite sending DELETE
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
                            //http:localhost:8003/mentors/deleteAward
                            // and mentorId in body!

                            isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
                            br = new BufferedReader(isr);
                            data = Parsers.parseFormData(br.readLine());

                            cardIdToDelete = Integer.parseInt(data.get("cardIdToDelete")); // TODO: in frontend
                            mentorsId = Integer.parseInt(data.get("mentorsId"));

                            this.mentorService.deleteAwardById(cardIdToDelete);
                            response = "award DELETE - done!";
//                         List<Award> awards = this.mentorService.getAllAwardsOfThisMentorByUserId(mentorsId);
//                         response = this.mapper.writeValueAsString(awards);

                        default:
                            //np. http://localhost:8003/mentors
                            //np. http://localhost:8003/mentorsqweqwe
                            List<User> users = this.mentorService.getAllMentors();
                            response = this.mapper.writeValueAsString(users);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            sendResponse(response, httpExchange, 404);
        }
        sendResponse(response, httpExchange, 200);
    }

    private boolean isGetDetailsAction(String action1) {
        return action1.matches("\\d+");
    }

    private boolean noActionProvided(String[] actions) {
        return actions.length == 2;
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

    private String handleActions2(String[] actions, String action) throws JsonProcessingException {
        switch (action) {
            case "details":
                //np. http://localhost:8003/mentors/details/2
                User user = this.mentorService.readUserFromDaoById(Integer.parseInt(actions[3]));
                return this.mapper.writeValueAsString(user);
            case "myQuests":
                // http://localhost:8003/mentors/myQuests/2  <--- mentorId
                int idOfMentor = Integer.parseInt(actions[3]);
                List<Quest> quests = this.mentorService.getAllQuestsOfThisMentorByUserId(idOfMentor);
                return this.mapper.writeValueAsString(quests);
            case "questsByMentor": // TODO: move to another handler
                List<QuestCountByMentor> questsCount = this.statisticsService.getQuestsCountByMentor();
                return this.mapper.writeValueAsString(questsCount);
            default:
                //np. http://localhost:8003/mentors
                //np. http://localhost:8003/mentorsqweqwe
                List<User> users = this.mentorService.getAllMentors();
                return this.mapper.writeValueAsString(users);
        }
    }

    private String handleActions(String[] actions, String action) throws JsonProcessingException {
        switch (action) {
            case "details":
                //np. http://localhost:8003/mentors/details/2
                User user = this.mentorService.readUserFromDaoById(Integer.parseInt(actions[3]));
                return this.mapper.writeValueAsString(user);
            case "myQuests":
                // http://localhost:8003/mentors/myQuests/2  <--- mentorId
                int idOfMentor = Integer.parseInt(actions[3]);
                List<Quest> quests = this.mentorService.getAllQuestsOfThisMentorByUserId(idOfMentor);
                return this.mapper.writeValueAsString(quests);
            case "questsByMentor": // TODO: move to another handler
                List<QuestCountByMentor> questsCount = this.statisticsService.getQuestsCountByMentor();
                return this.mapper.writeValueAsString(questsCount);
            default:
                //np. http://localhost:8003/mentors
                //np. http://localhost:8003/mentorsqweqwe
                List<User> users = this.mentorService.getAllMentors();
                return this.mapper.writeValueAsString(users);
        }
    }
}