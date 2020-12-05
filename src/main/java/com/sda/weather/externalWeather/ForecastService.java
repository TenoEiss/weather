package com.sda.weather.externalWeather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final WeatherConfig weatherConfig;
    private final ForecastRepository forecastRepository;
    private final ForecastMapper forecastMapper;

    public ForecastDto getForecast(String localization){
        UriComponents build = UriComponentsBuilder.fromHttpUrl(weatherConfig.getUri())
                .queryParam("access_key", weatherConfig.getApiKey())
                .queryParam("query", localization)
                .queryParam("units", weatherConfig.getUnits())
                .queryParam("lang", weatherConfig.getLang())
                .build();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(build.toUri(),String.class);

        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            throw new BadRequestException("wonka 2 bad sowy!");
        }

        String responseBody = responseEntity.getBody();

        try {
            ForecastDto forecastDto = objectMapper.readValue(responseBody, ForecastDto.class);
            return saveForecastToDatabase(forecastDto);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("I am lazy");
        }
    }
    public ForecastDto saveForecastToDatabase(ForecastDto forecastDto){
        Forecast forecast = new Forecast();
        forecast.setTemperature(forecastDto.getTemperature());
        forecast.setAirPressure(forecastDto.getAirPressure());
        forecast.setHumidity(forecastDto.getHumidity());
        forecast.setWindSpeed(forecastDto.getWindSpeed());
        forecast.setWindDirection(forecastDto.getWindDirection());
        forecast.setWindDegree(forecastDto.getWindDegree());
        return forecastMapper.maptoForecastDto(forecastRepository.save(forecast));
    }
}
