package com.github.kirvolque.instrumentationtemplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoadTest {
    @LocalServerPort
    private int port; // Inject the random port used by Spring Boot

    @Test
    public void loadItemsEndpoints() {
        RestTemplate restTemplate = new RestTemplate();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> responses = new ArrayList<>();

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    String baseUrl = "http://localhost:" + port; // Use random port provided by Spring
                    String response1 = restTemplate.getForObject(baseUrl + "/api/items", String.class);
                    responses.add("GET /api/items: " + response1);

                    String response2 = restTemplate.getForObject(baseUrl + "/api/items/1", String.class);
                    responses.add("GET /api/items/1: " + response2);

                    String response3 = restTemplate.postForObject(baseUrl + "/api/items", "NewItem", String.class);
                    responses.add("POST /api/items: " + response3);
                } catch (Exception e) {
                    responses.add("Request failed: " + e.getMessage());
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            executorService.submit(task);
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            // Wait until all threads finish
        }

        // Print responses after all requests are complete
        System.out.println("Server responses:");
        responses.forEach(System.out::println);
    }
}