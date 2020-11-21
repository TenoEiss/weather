package com.sda.weather.localization;

import lombok.*;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {
    Long id;
    String cityName;
    String region;
    String country;
    float longitude;
    float latitude;
}
