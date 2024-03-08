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
 * {@link ForecastReport} pojo class that represent each period, this will be nested as an array of object
 * in the parent json for {@link AirportWeatherServiceResponse}
 */
public class ForecastReport {
    /*A forecast report for the next two periods (the second and third conditions nodes in /report/forecast/conditions)
    that contains the following entries*/
    private String timeOffset; // The time offset from the start of the period (/report/forecast/period/dateStart) in hrs:min - Temp (F)
    private float windSpeed; //Wind Speed (MPH)
    private float tempF; //Temperature needs to be converted from Celsius to Fahrenheit
    private String windDirection; //Wind Direction (degrees true)
}