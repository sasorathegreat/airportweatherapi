package com.airport.weather.demo.models.foreflight.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conditions implements Serializable {
    @JsonProperty("text")
    private String text;
    @JsonProperty("lat")
    private float lat; //"lat": 30.19452702415562,
    @JsonProperty("lon")
    private float lon; //"lon": -97.66987607813589,
    @JsonProperty("tempC")
    private float tempC; //"tempC": 21.0,
    @JsonProperty("relativeHumidity")
    private String relativeHumidity;
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("forecast")
    private Forecast forecast;
    @JsonProperty("period")
    private Period period;


}
