package com.sda.weather.localization;

import com.sda.weather.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    Localization createLocalization(LocalisationDefinition localisationDefinition) throws Exception {
        float latitude = localisationDefinition.getLatitude();
        float longitude = localisationDefinition.getLongitude();
        String country = localisationDefinition.getCountry();
        String cityName = localisationDefinition.getCityName();
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
            localization.setRegion(localisationDefinition.getRegion().toString());
            localization.setCountry(country);
            localization.setLongitude(longitude);
            localization.setLatitude(latitude);
            return localization;
        }
    }
}
