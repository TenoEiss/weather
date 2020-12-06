package com.sda.weather.externalWeather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ForecastMapper {
    ForecastDto mapToForecastDto(Forecast newForecast){
        return ForecastDto.builder()
                .temperature(newForecast.getTemperature())
                .airPressure(newForecast.getAirPressure())
                .humidity(newForecast.getHumidity())
                .windSpeed(newForecast.getWindSpeed())
                .windDirection(newForecast.getWindDirection())
                .build();
    }
    Forecast mapExternalForecastToForecast(ExternalForecastResponse response){
        return Forecast.builder()
                .temperature(response.getWeatherList())
    }
}
