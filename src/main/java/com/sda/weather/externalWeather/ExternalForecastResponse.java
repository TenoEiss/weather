package com.sda.weather.externalWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExternalForecastResponse {

    @JsonProperty("list")
    private List<ForecastDetailsFromJson> weatherList;

    @Data
    static class ForecastDetailsFromJson {

        @JsonProperty("main")
        private WeatherDetails weatherDetails;

        @JsonProperty("dt_txt")
        private String dateTime;

        private WindDetails wind;

        @Data
        static class WeatherDetails{
            @JsonProperty("temp")
            private double temperature;
            private int pressure;
            private int humidity;
        }

        @Data
        static class WindDetails {
            private double speed;
            private double deg;
        }
    }
}
