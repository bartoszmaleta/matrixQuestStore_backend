package com.company.handler;

import com.company.dao.StudentDetailsDao;
import com.company.dao.StudentDetailsDaoDb;
import com.company.model.user.Role;
import com.company.model.user.Student;
import com.company.model.user.User;
import com.company.service.AdminService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegisterHandler implements HttpHandler {
    private StudentDetailsDao studentDetailsDao;
    private AdminService adminService;

    public RegisterHandler() {
        this.studentDetailsDao = new StudentDetailsDaoDb();
        this.adminService = new AdminService();

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "data not saved";
        String method = exchange.getRequestMethod(); // POST or GET

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            Map<String, String> data = parseFormData(br.readLine());

            User student = new Student();

            Role roleEnum = decideRole(data.get("roleId"));

            student.setName(data.get("name"))
                    .setSurname(data.get("surname"))
                    .setLogin(data.get("login"))
                    .setPassword(data.get("password"))
                    .setEmail(data.get("email"))
                    .setRole(roleEnum)
//                    .setStudent()     // TODO: don't know if only student could register?
                    .setAvatarSource(data.get("avatarPath"));


            try {
                this.adminService.addUser(student);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            response = "data saved - POST method";
        } else if (method.equals("GET")) {
            // TODO: ??
            response = "data get - GET method";
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());

        os.close();
    }

    private Role decideRole(String data) {
        int roleId = Integer.parseInt(data);
        Role role = null;
        if (roleId == 1) {
            role = Role.ADMIN;
        } else if (roleId == 2) {
            role = Role.MENTOR;
        } else if (roleId == 3) {
            role = Role.STUDENT;
        }
        return role;
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }


}
