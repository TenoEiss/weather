package com.sda.weather.localization;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
public class LocalizationDefinition {
    private String cityName;
    private String region;
    private String country;
    private float longitude;
    private float latitude;


}
