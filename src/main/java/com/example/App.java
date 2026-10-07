package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {

        int port = 8080;

        HttpServer server = HttpServer.create(
                new InetSocketAddress(port), 0);

        server.createContext("/", App::handleRequest);

        server.start();

        System.out.println("Java application started on port " + port);
    }

    private static void handleRequest(HttpExchange exchange)
            throws IOException {

        String response = """
                Hello from Java!

                Application: DevOps Demo
                Environment: Local Windows
                Deployment: Azure DevOps
                Version : 2
                """;

        exchange.sendResponseHeaders(200, response.length());

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(response.getBytes());
        }
    }
}
