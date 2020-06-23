package com.company.handler;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.models.users.Role;
import com.company.models.users.Student;
import com.company.models.users.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegisterHandler implements HttpHandler {
    private UserDao userDao;

    public RegisterHandler() {
        this.userDao = new UserDaoDb();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "no data ";
        String method = exchange.getRequestMethod(); // POST or GET

        if (method.equals("POST")) {
            // retrieve data
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);

//            String data = br.readLine();

            Map<String, String> data = parseFormData(br.readLine());

            System.out.println(data.get("name"));
            System.out.println(data.get("surname"));
            System.out.println(data.get("login"));
            System.out.println(data.get("password"));
            System.out.println(data.get("email"));
            System.out.println(data.get("roleId"));
            System.out.println(data.get("userDetailId"));

            System.out.println("test");
            User student = new Student();

            Role roleEnum = decideRole(data.get("roleId"));

            student.setName(data.get("name"))
                    .setSurname(data.get("surname"))
                    .setLogin(data.get("login"))
                    .setPassword(data.get("password"))
                    .setEmail(data.get("email"))
                    .setRole(roleEnum)
                    .setUserDetailId(Integer.parseInt(data.get("userDetailId")));

            System.out.println("test2");

            System.out.println("toString = " + student.toString());
            System.out.println(data);

            userDao.insert(student);

            response = "data saved - POST method";
        } else if (method.equals("GET")) {

            response = "data get - GET method";
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());

        os.close();
    }

    private Role decideRole(String data) {
        int roleId = Integer.parseInt(data);
//        int roleId = Integer.parseInt((String) data.get("roleId"));
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
