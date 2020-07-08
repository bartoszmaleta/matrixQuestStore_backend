package com.company.handler;

import com.company.dao.StudentDetailsDao;
import com.company.dao.StudentDetailsDaoDb;
import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.Role;
import com.company.model.user.Student;
import com.company.model.user.User;
import com.company.service.AdminService;
import com.company.service.EmployeeService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

            System.out.println("name = " + data.get("name"));
            System.out.println("surname = " + data.get("surname"));
            System.out.println("login = " + data.get("login"));
            System.out.println("password = " + data.get("password"));
            System.out.println("email = " + data.get("email"));
            System.out.println("roleId = " + data.get("roleId"));
            System.out.println("avatarPath = " + data.get("avatarPath"));

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

            System.out.println("test2");

            System.out.println("toString = " + student.toString());
            System.out.println(data);

            this.adminService.addUser(student);

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
