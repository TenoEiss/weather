package com.sda.weather.externalWeather;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Forecast {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String temperature;
    private String airPressure;
    private String windDirection;
    private String windDegree;
    private String windSpeed;
    private String humidity;
}
