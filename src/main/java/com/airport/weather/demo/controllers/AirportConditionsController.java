package com.airport.weather.demo.controllers;

import com.airport.weather.demo.models.AirportWeatherServiceResponse;
import com.airport.weather.demo.services.AirportWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The {@link AirportConditionsController} rest controller class, contains a single endpoint that will query 2 api for airport and weather informaton.
 * This class contains a service class {@link AirportWeatherService} that will perform most of the business layer logic.
 */
@RestController
@RequestMapping("/api/weather/airports")
public class AirportConditionsController {

    /**
     * A {@link AirportWeatherService} a service object that performs the querying of Foreflight API and other calculations.
     */
    @Autowired
    private AirportWeatherService airportWeatherService;

    /**
     * A Getter method endpoint that will retrieve both the Airport and Weather information from foreflight api and put them together in a json response object.
     * @param values A list of {@link String}
     * @return A List of {@link AirportWeatherServiceResponse}
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirportWeatherServiceResponse>> getAirportWeather(@RequestParam Optional<List<String>> values){

        //TODO: Add Validate the Request Input
        if (values.isEmpty()){
            //return Value not found or 404 Not found message
            return new ResponseEntity("null", HttpStatus.OK);
        }
        Optional<List<AirportWeatherServiceResponse>> result = airportWeatherService.findWeatherByAirport(values.get());
        return result.map(ResponseEntity::ok).orElseGet(() -> (ResponseEntity<List<AirportWeatherServiceResponse>>) ResponseEntity.notFound());
    }
}
