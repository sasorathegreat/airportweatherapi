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
public class Visibility  implements Serializable {
    @JsonProperty("distanceSm")
    private String distanceSm;
    @JsonProperty("prevailingVisSm")
    private String prevailingVisSm;
}
