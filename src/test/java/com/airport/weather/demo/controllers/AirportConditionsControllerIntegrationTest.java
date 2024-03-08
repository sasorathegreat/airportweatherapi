package com.airport.weather.demo.controllers;

import com.airport.weather.demo.models.AirportWeatherServiceResponse;
import com.airport.weather.demo.services.AirportWeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirportConditionsControllerIntegrationTest {
    private static final String API = "api/weather/airports?values=kaus,clt";

    @LocalServerPort
    private int port = 8080;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    AirportWeatherService airportWeatherService;

    @BeforeEach
    public void cleanup(){
        //userRepository.deleteAll();
    }

    @Test
    public void testValid() {
        webTestClient.get()
                .uri(API)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testValidOutput() {
         webTestClient.get()
                .uri(API)
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                 .expectHeader().valueEquals("Content-Type", "application/json;charset=UTF-8")
                 .expectBody(List.class)
                 .consumeWith(result -> {
                     Assert.isTrue(((AirportWeatherServiceResponse) result.getResponseBody().get(0)).getAirportIdentifier().equals("??"), "Identifier Not equal");
                     Assert.isTrue(((AirportWeatherServiceResponse) result.getResponseBody().get(0)).getAirportName().equals("Austin-Bergstrom International"), "Airport name should be austin");
                     Assert.isTrue(((AirportWeatherServiceResponse) result.getResponseBody().get(0)).getAvailableRunways() == 2, "Should be 2");

                     Assert.isTrue(((AirportWeatherServiceResponse) result.getResponseBody().get(1)).getAirportIdentifier().equals("??"), "Identifier Not equal");
                     Assert.isTrue(((AirportWeatherServiceResponse) result.getResponseBody().get(1)).getAirportName().equals("Charlotte/Douglas International"), "Airport name should be austin");
                     Assert.isTrue(((AirportWeatherServiceResponse) result.getResponseBody().get(1)).getAvailableRunways() == 4, "Should be 2");
                 });

    }
}
