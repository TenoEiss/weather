package com.sda.weather.externalWeather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("com.sda.weather.weather-api")//todo @ConfigurationPropertiesScan czy cos
public class WeatherConfig {
    private String apiKey;
}
