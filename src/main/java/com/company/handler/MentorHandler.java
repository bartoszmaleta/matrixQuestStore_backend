package com.company.handler;

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
import java.sql.Timestamp;
import java.util.*;

public class MentorHandler extends HandlersParent implements HttpHandler {
    private final MentorService mentorService;
    private final StatisticsService statisticsService;
    private final ObjectMapper mapper;

    public MentorHandler() {
        this.mentorService = new MentorService();
        this.statisticsService = new StatisticsService();
        this.mapper = new ObjectMapper();

        // TODO: move to another handler
    }


    // TODO: check which one is working??????
    public MentorHandler(MentorService mentorService, StatisticsService statisticsService, ObjectMapper objectMapper) {
        this.mentorService = new MentorService();
        this.statisticsService = new StatisticsService();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String url = httpExchange.getRequestURI().getRawPath();
        String[] actions = url.split("/");
        String defaultAction = "";
        String action = (noActionProvided(actions)) ? defaultAction : (isGetDetailsAction(actions[2]) ? "details" : actions[2]);
        System.out.println("action = " + action);

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
                    switch (action) {
                        case "addAward":
                            //http:localhost:8003/mentors/addAward
                            // and mentorId in body!
                            List<Award> awards = addAwardAndGetAllAwardsAction(httpExchange);
                            response = mapper.writeValueAsString(awards);
                            break;
                        case "deleteAward":
                            //http:localhost:8003/mentors/deleteAward
                            // and mentorId in body!
                            response = deleteAwardAction(httpExchange);
                        case "addQuest":
                            //http:localhost:8003/mentors/addQuest
                            List<Quest> quests = addQuestAndGetAllQuestsAction(httpExchange);
                            response = mapper.writeValueAsString(quests);
                            break;

                        case "deleteQuest":
                            //http:localhost:8003/mentors/deleteQuest
                            response = deleteAction(httpExchange);
                    }
                    break;
                }
                case "DELETE": {
                    // TODO: can't get here, because i get 'OPTIONS" method, despite sending DELETE
                    switch (action) {
                        case "deleteAward":
                            //http:localhost:8003/mentors/deleteAward
                            // and mentorId in body!
                            response = deleteAwardAndGetAllAwards(httpExchange);
                            break;
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
            super.sendResponse(response, httpExchange, 404);
        }
        super.sendResponse(response, httpExchange, 200);
    }

    private String deleteAwardAndGetAllAwards(HttpExchange httpExchange) throws IOException {
        Map<String, String> data;
        data = super.getDataMap(httpExchange);

        int cardIdToDelete = Integer.parseInt(data.get("cardIdToDelete")); // TODO: in frontend
        int mentorsId = Integer.parseInt(data.get("mentorsId")); // TODO: which one is okay?
        // TODO: cardIdToDelete or mentorsId

        this.mentorService.deleteAwardById(cardIdToDelete);
//        List<Award> awards = this.mentorService.getAllAwardsOfThisMentorByUserId(mentorsId);
//        String response = this.mapper.writeValueAsString(awards);
        return "award DELETE - done!";
    }

    private List<Award> addAwardAndGetAllAwardsAction(HttpExchange httpExchange) throws IOException {
        Map<String, String> data = super.getDataMap(httpExchange);
        Award award = new Award();
        Date date = new Date();
        int mentorsId = Integer.parseInt(data.get("mentorsId"));

        List<Award> awards;
        award.setTitle(data.get("title"))
                .setImageSrc(data.get("imageSrc"))
                .setDescription(data.get("description"))
                .setPrice(Integer.parseInt(data.get("price")))
                .setMentorId(mentorsId)
                .setDataCreation(new Timestamp(date.getTime()));

        this.mentorService.addAwardToDatabase(award);

        awards = this.mentorService.getAllAwardsOfThisMentorByUserId(mentorsId);
        return awards;
    }

    private String deleteAwardAction(HttpExchange httpExchange) throws IOException {
        Map<String, String> data;
        int mentorsId;
        data = super.getDataMap(httpExchange);

        int cardIdToDelete;     // TODO: move upwards!!!

        cardIdToDelete = Integer.parseInt(data.get("id")); // TODO: in frontend
        mentorsId = Integer.parseInt(data.get("mentorsId")); // TODO: which one is okay?
        // TODO: cardIdToDelete or mentorsId

        this.mentorService.deleteAwardById(cardIdToDelete);
        return "award DELETE - done!";
    }

    private List<Quest> addQuestAndGetAllQuestsAction(HttpExchange httpExchange) throws IOException {
        Map<String, String> data = super.getDataMap(httpExchange);
        Quest quest = new Quest();
        Date date = new Date(); // TODO: add Date quest c-tor
        int mentorsId = Integer.parseInt(data.get("mentorsId"));

        List<Quest> quests;
        quest.setTitle(data.get("title"))
                .setImageSrc(data.get("imageSrc"))
                .setDescription(data.get("description"))
                .setPrice(Integer.parseInt(data.get("price")))
                .setMentorId(mentorsId);

        this.mentorService.addQuestToDatabase(quest);
        quests = this.mentorService.getAllQuestsOfThisMentorByUserId(mentorsId);
        return quests;
    }

    private String deleteAction(HttpExchange httpExchange) throws IOException {
        Map<String, String> data = super.getDataMap(httpExchange);
        this.mentorService.deleteQuestById(Integer.parseInt(data.get("id")));
        return "Quest successfully deleted";
    }

    private boolean isGetDetailsAction(String action1) {
        return action1.matches("\\d+");
    }

    private boolean noActionProvided(String[] actions) {
        return actions.length == 2;
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