package com.sda.weather.externalWeather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForecastDto {
    String temperature;
    String airPressure;
    String windDirection;
    String windSpeed;
    String humidity;
    String date;
}
