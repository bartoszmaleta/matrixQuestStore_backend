package com.company.helpers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponses {
    private HttpExchange exchange;
    private String response;

    public HttpResponses(HttpExchange exchange, String response) {
        this.exchange = exchange;
        this.response = response;
    }

    public HttpResponses() {
    }

    public void sendResponse200() throws IOException {
        prepareResponse200(exchange, response);
    }

    public void sendResponse200(HttpExchange httpExchange, String response) throws IOException {
        prepareResponse200(httpExchange, response);
    }

    private void prepareResponse200(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
