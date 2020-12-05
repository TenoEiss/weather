package com.sda.weather.externalWeather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ForecastMapper {
    ForecastDto maptoForecastDto(Forecast newForecast){
        return ForecastDto.builder().id(newForecast.getId())
                .temperature(newForecast.getTemperature())
                .airPressure(newForecast.getAirPressure())
                .humidity(newForecast.getHumidity())
                .windSpeed(newForecast.getWindSpeed())
                .windDegree(newForecast.getWindDegree())
                .windDirection(newForecast.getWindDirection())
                .build();
    }
}
