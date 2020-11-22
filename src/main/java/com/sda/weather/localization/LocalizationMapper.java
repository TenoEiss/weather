package com.sda.weather.localization;

import org.springframework.stereotype.Component;

@Component
class LocalizationMapper {

    LocalizationDto mapToLocalizationDto(Localization localization) {
        LocalizationDto localizationDto = new LocalizationDto();
        localizationDto.setId(localization.getId());
        localizationDto.setCityName(localization.getCityName());
        localizationDto.setRegion(localization.getRegion().orElse(null));
        localizationDto.setCountry(localization.getCountry());
        localizationDto.setLongitude(localization.getLongitude());
        localizationDto.setLatitude(localization.getLatitude());
        return localizationDto;
    }

    LocalizationDefinition mapToLocalizationDefinition(LocalizationDto localizationDto) {
        return LocalizationDefinition.builder()
                .cityName(localizationDto.getCityName())
                .region(localizationDto.getRegion())
                .country(localizationDto.getCountry())
                .longitude(localizationDto.getLongitude())
                .latitude(localizationDto.getLatitude())
                .build();
    }
}
