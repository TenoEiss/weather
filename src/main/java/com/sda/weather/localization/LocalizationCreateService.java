package com.sda.weather.localization;

import com.sda.weather.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    Localization createLocalization(LocalizationDefinition localizationDefinition){
        float latitude = localizationDefinition.getLatitude();
        float longitude = localizationDefinition.getLongitude();
        String country = localizationDefinition.getCountry();
        String cityName = localizationDefinition.getCityName();
        if (cityName.isBlank() ||
                country.isBlank() ||
                longitude > 180 ||
                longitude < -180 ||
                latitude > 90 ||
                latitude < -90) {
            throw new BadRequestException("Data provided in the creation query is incorrect");
        } else {
            Localization localization = new Localization();
            localization.setCityName(cityName);
            localization.setRegion(localizationDefinition.getRegion().toString());
            localization.setCountry(country);
            localization.setLongitude(longitude);
            localization.setLatitude(latitude);
            return localizationRepository.save(localization);
        }
    }
}
