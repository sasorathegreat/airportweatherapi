package com.airport.weather.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * {@link WeatherReport} pojo class that will represent the current weather report for each airport
 */
public class WeatherReport {
    // A current weather report that contains the following:
    private float tempF;
    private String RelativeHumidity; //(%)
    private String summaryOfCloudCoverage; //(text string)
    private String greatestAmountOfCoverage; // This is the greatest amount of coverage listed if any
    private String Visibility; //(Statute Miles)
    private float windSpeed; //(MPH)
    // Wind Direction (cardinal directions to secondary-intercardinal precision)
    private String windDirection;
}
