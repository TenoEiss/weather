package com.sda.weather.localization;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
public class LocalisationDefinition {
    private String cityName;
    private Optional<String> region;
    private String country;
    private float longitude;
    private float latitude;


}
