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
        private WeatherDetailsFromJson weatherList;

        @JsonProperty("dt_txt")
        private String dateTime;

        private WindDetailsFromJson wind;

        @Data
        static class WeatherDetailsFromJson {
            @JsonProperty("temp")
            private double temperature;
            private int pressure;
            private int humidity;
        }

        @Data
        static class WindDetailsFromJson {
            private double speed;
            private double deg;
        }
    }
}
