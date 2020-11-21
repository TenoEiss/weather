package com.sda.weather.localization;

import org.springframework.stereotype.Component;

@Component
class LocalizationMapper {

    LocalizationDto mapToLocalizationDto(Localization localization) {
        LocalizationDto localizationDto = new LocalizationDto();
        localizationDto.setId(localization.getId());
        localizationDto.setCityName(localization.getCityName());
        localizationDto.setRegion(localization.getRegion());
        localizationDto.setCountry(localization.getCountry());
        localizationDto.setLongitude(localization.getLongitude());
        localizationDto.setLatitude(localization.getLatitude());
        return localizationDto;
    }
}
