package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {
    Long id;
    @NotEmpty
    String cityName;
    String region;
    @NotEmpty
    String country;
    @Min(-180)
    @Max(180)
    float longitude;
    @Min(-90)
    @Max(90)
    float latitude;
}
