package com.airport.weather.demo.models.foreflight.airport;

import com.airport.weather.demo.models.foreflight.weather.Report;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * {@link AirportResponse} class is the response object for the Foreflight Airport API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportResponse implements Serializable {
    @JsonProperty("siteNumberCode")
    private String siteNumberCode;
    @JsonProperty("icao")
    private String icao;
    @JsonProperty("iata")
    private String iata;
    @JsonProperty("faaCode")
    private String faaCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("stateCode")
    private String stateCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("runways")
    private List<Object> runways;
}
