package com.sda.weather.localization;

import lombok.*;

@Getter
@Builder
public class LocalizationDefinition {
    private final String cityName;
    private final String region;
    private final String country;
    private final float longitude;
    private final float latitude;


}
