package com.sda.weather.externalWeather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exceptions.InternalServerException;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
//    private final ExternalWeatherProperties externalWeatherProperties;
    private final LocalizationFetchService localizationFetchService;
    private final ForecastRepository forecastRepository;
    private final ForecastMapper forecastMapper;

    public Forecast getForecast(Long id, Integer period) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        String cityName = localization.getCityName();

        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", cityName)
                .queryParam("appid", "bb1f5dd323a8a97af4ce01cafcb5d2de")
                .queryParam("units", "metric")
                .queryParam("lang", "en")
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        try {
            ExternalForecastResponse externalForecastResponse = objectMapper.readValue(response, ExternalForecastResponse.class);



            // todo fetch correct single forecast
            ExternalForecastResponse.ForecastDetailsFromJson singleForecast = externalForecastResponse.getWeatherList().get(0);
            // todo map singleForecast -> Forecast

            return forecastRepository.save(null);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Couldn't collect data from external service.");
        }
    }
}
