package com.airport.weather.demo.models.foreflight.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind  implements Serializable {
    @JsonProperty("speedKts")
    private float speedKts;
    @JsonProperty("direction")
    private int direction;
    @JsonProperty("from")
    private int from;
    @JsonProperty("variable")
    private boolean variable;
}
