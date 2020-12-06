package com.sda.weather.externalWeather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@Validated
public class ForecastController {
    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;

    @GetMapping("/localization/{id}/forecast")
    ForecastDto getForecast(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1") @Min(1) @Max(5) Integer period) {
        Forecast forecast = forecastService.getForecast(id, period);
        return forecastMapper.mapToForecastDto(forecast);
    }
}
