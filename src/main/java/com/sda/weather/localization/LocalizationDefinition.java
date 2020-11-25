package com.sda.weather.localization;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class LocalizationDefinition {
    private String cityName;
    private String region;
    private String country;
    private float longitude;
    private float latitude;


}
