package com.airport.weather.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportWeatherServiceResponse {
    private String airportIdentifier;
    private String airportName;
    private int availableRunways;
    private String airportLat;
    private String airportLong;
    private WeatherReport weatherReport;
    private List<ForecastReport> forecastReport;
}
