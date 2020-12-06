package com.sda.weather.externalWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExternalForecastResponse {
    //    @JsonProperty
//    private String cod;
//    @JsonProperty
//    private String message;
//    @JsonProperty
//    private String cnt;
    @JsonProperty("list")
    private List<ForecastDetailsFromJson> weatherList;

    @Data
    static class ForecastDetailsFromJson {

        @JsonProperty("main")
        private List<WeatherDetailsFromJson> weatherList;

        @Data
        static class WeatherDetailsFromJson {
            @JsonProperty("temp")
            private double temperature;
            private int pressure;
            private int humidity;
            private List<WindDetailsFromJson> wind;
            @JsonProperty("dt_txt")
            private String dateTime;

            @Data
            static class WindDetailsFromJson {
                private double speed;
                private double deg;
            }
        }
    }

//    @JsonProperty("city")
//    private List<CityDetailsFromJson> cityList;
//
//    @Data
//    static class CityDetailsFromJson {
//        private String name;
//    }
}
