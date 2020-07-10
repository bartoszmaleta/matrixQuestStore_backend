package com.company.handler;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.Transaction;
import com.company.model.user.Mentor;
import com.company.model.user.Role;
import com.company.model.user.Student;
import com.company.model.user.User;
import com.company.service.AdminService;
import com.company.service.MentorService;
import com.company.service.TransactionsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentHandler implements HttpHandler {
    private final ObjectMapper mapper;
    private final AdminService adminService;
    private TransactionsService transactionsService;

    public StudentHandler() {
        this.mapper = new ObjectMapper();
        this.adminService = new AdminService();
        this.transactionsService = new TransactionsService();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String url = exchange.getRequestURI().getRawPath();
        String[] actions = url.split("/");
//        String action = (actions.length == 2) ? "" : (actions[2].matches("\\d+")) ? actions[2];
        String action = null;
        System.out.println("null length = " + action);
        if (actions.length == 2) {
            action = "";
            System.out.println("empty action = " + action);
        }
        String response = "";
        String method = exchange.getRequestMethod(); // POST or GET


        System.out.println("array methods = " + Arrays.toString(actions));

        System.out.println("actions length = " + actions.length);

        try {
            if (method.equals("GET")) {
                if (actions.length == 4) { // TODO: should be transactions!!!!!!!
                    int studentId = Integer.parseInt(actions[3]);
                    System.out.println("studentId to transactions = " + studentId);

                    List<Transaction> transactions = this.transactionsService.getTransactionsByStudentId(studentId);
                    System.out.println("transactions arrayList = " + transactions);
                    System.out.println("transactions mapper = " + this.mapper.writeValueAsString(transactions));
                    response = this.mapper.writeValueAsString(transactions);
                } else if (actions.length == 3) {
                    System.out.println("if length");
                    //np. http://localhost:8003/users/23

                    User student = this.adminService.readUserFromDaoById(Integer.parseInt(actions[2]));
                    System.out.println("if length2");
                    if (student.getRole() == Role.STUDENT) {
                        student = (Student) student;
                    }
                    response = this.mapper.writeValueAsString(student);
                } else {
                    System.out.println("else");
                    List<User> students = this.adminService.getAllStudents();
                    response = this.mapper.writeValueAsString(students);
                }
            }
        } catch (IOException e) {
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