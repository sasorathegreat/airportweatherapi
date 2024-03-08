package com.airport.weather.demo.services;

import com.airport.weather.demo.models.AirportWeatherServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * {@link AirportWeatherService} service interface that will contains all the methods related to retrieving the Airport and Weather information
 */
@Service
public interface AirportWeatherService {

    /**
     * Method is the main business logic that queries the airport and weather api and does additional calculation and conversions
     *
     * @param airportCodes List of {@link String}
     * @return A List of {@link AirportWeatherServiceResponse} objects
     */
    public Optional<List<AirportWeatherServiceResponse>> findWeatherByAirport(List<String> airportCodes);
}
