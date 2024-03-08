package com.airport.weather.demo.impls;

import com.airport.weather.demo.models.AirportWeatherServiceResponse;
import com.airport.weather.demo.models.ForecastReport;
import com.airport.weather.demo.models.WeatherReport;
import com.airport.weather.demo.models.foreflight.airport.AirportResponse;
import com.airport.weather.demo.models.foreflight.weather.WeatherResponse;
import com.airport.weather.demo.services.AirportWeatherService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * {@link AirportWeatherServiceImpl} class in the implementation logic of {@link AirportWeatherService}.
 * This implementation class will configure the {@link RestTemplate}, gets the Weather and Airport information from Forefllight API,
 * then build the necessary information into the {@link AirportWeatherServiceResponse} object
 */
@Component
public class AirportWeatherServiceImpl implements AirportWeatherService {

    /**
     * Wanted to put this in the application.properties but lost time, sorry
     */
    private String airportResourceUrl = "https://qa.foreflight.com/airports/";
    private String weatherResourceUrl = "https://qa.foreflight.com/weather/report/";

    private final RestTemplate restTemplate;

    /**
     * Overriding the RestTemplate request header to always include the foreflight header parameters and authentication
     * @param restTemplateBuilder
     */
    public AirportWeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .defaultHeader("ff-coding-exercise","1")
                .basicAuthentication("ff-interview", "@-*KzU.*dtP9dkoE7PryL2ojY!uDV.6JJGC9")
                .build();
    }
    /**
     * Method for retrieving the airport information based on the airport code
     * @param {@Link String} airportCode
     * @return A String
     */
    private AirportResponse getAirport(String airportCode) {
        ResponseEntity<AirportResponse> response = restTemplate.getForEntity(this.airportResourceUrl + airportCode, AirportResponse.class);
        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
    }

    /**
     * Method for retrieving the weather based on a valid airport code
     * @param {@Link String} airportCode
     * @return A {@link WeatherResponse} object
     */
    private WeatherResponse getWeatherByAirportCode(String airportCode) {
        ResponseEntity<WeatherResponse> response  = restTemplate.getForEntity(this.weatherResourceUrl + airportCode, WeatherResponse.class);
        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
    }

    /**
     * Method is the main business logic that queries the airport and weather api and does additional calculation and conversions
     *
     * @param airportCodes List of {@link String}
     * @return A List of {@link AirportWeatherServiceResponse} objects
     */
    @Override
    public Optional<List<AirportWeatherServiceResponse>> findWeatherByAirport(List<String> airportCodes) {
        List<AirportWeatherServiceResponse> response = new ArrayList<AirportWeatherServiceResponse>();
        airportCodes.forEach((item)-> {
            AirportResponse airportResponse = this.getAirport(item);
            WeatherResponse weatherResponse = this.getWeatherByAirportCode(item);

            if (airportResponse != null && weatherResponse != null){
                WeatherReport weatherReport = buildWeatherReport(weatherResponse);
                List<ForecastReport> forecastReportBuilder = buildForecastReport(weatherResponse);

                response.add(AirportWeatherServiceResponse.builder()
                        .airportIdentifier("??")
                        .airportName(airportResponse.getName())
                        .availableRunways(airportResponse.getRunways().size())
                        .airportLat(airportResponse.getLatitude())
                        .airportLong(airportResponse.getLongitude())
                        .weatherReport(weatherReport)
                        .forecastReport(forecastReportBuilder)
                        .build());
            }

        });
        return Optional.of(response);
    }

    /**
     * Method for creating the Forecast Report base on the current Airport and weather conditions
     * @param weatherResponse object representation of {@link WeatherResponse}
     * @return A List of {@link ForecastReport} objects
     */
    private static List<ForecastReport> buildForecastReport(WeatherResponse weatherResponse) {
        List<ForecastReport> forecastReportBuilder = new ArrayList<ForecastReport>();
        weatherResponse.getReport().getForecast().getConditions().forEach((fc)->{
            forecastReportBuilder.add(ForecastReport.builder()
                    .timeOffset(fc.getPeriod().getDateStart().substring(11, 16))
                    .windSpeed(convertKtsToMph( (ObjectUtils.isEmpty(fc.getWind()) ? 0 : fc.getWind().getSpeedKts() )))
                    .tempF(convertCelToFah(fc.getTempC()))
                    .windDirection("??")
                    .build());

        });
        return forecastReportBuilder;
    }

    /**
     * Method for creating the Weather Report based on the current Airport.
     * @param weatherResponse
     * @return
     */
    private static WeatherReport buildWeatherReport(WeatherResponse weatherResponse) {
        return WeatherReport.builder()
                .tempF(convertCelToFah(weatherResponse.getReport().getConditions().getTempC()))
                .RelativeHumidity(weatherResponse.getReport().getConditions().getRelativeHumidity() + "%")
                .Visibility(weatherResponse.getReport().getConditions().getVisibility().getDistanceSm())
                .windSpeed(convertKtsToMph(weatherResponse.getReport().getConditions().getWind().getSpeedKts()))
                .windDirection("??")
                .build();
    }

    /**
     * Method for converting Celsius to fahrenheit
     * @param value of a temperature typed as a {@link float}
     * @return A calculated {@link float} value for fahrenheit
     */
    private static float convertCelToFah(float value) {
        if (ObjectUtils.isEmpty(value)) {
            return value;
        }
        return value == 0 ? value :(float) (value * 1.8) + 32;
    }

    /**
     * Method for converting KTS speed to MPH
     * @param value of wind speed typed as a {@link float}
     * @return A calculated {@link float} value for fahrenheit
     */
    private static float convertKtsToMph(float value){
        if (ObjectUtils.isEmpty(value)) {
            return value;
        }
        return value == 0 ? value : (float) (value * 1.152);
    }
}
