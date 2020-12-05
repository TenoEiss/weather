package com.sda.weather.localization;

import com.sda.weather.exceptions.BadRequestException;
import com.sda.weather.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    Localization createLocalization(LocalizationDefinition localizationDefinition) {
        float latitude = localizationDefinition.getLatitude();
        float longitude = localizationDefinition.getLongitude();
        String country = localizationDefinition.getCountry();
        String cityName = localizationDefinition.getCityName();
        if (cityName.isBlank() || country.isBlank() || longitude > 180 || longitude < -180 || latitude > 90 || latitude < -90) {
            throw new BadRequestException("Data provided in the creation query is incorrect");
        }
        if(localizationDefinition == null){
            throw new InternalServerException("null haha!");
        }
        Localization localization = new Localization();
        if (localizationDefinition.getRegion().isBlank()) {
            localization.setRegion(null);
        } else {
            localization.setRegion(localizationDefinition.getRegion());
        }
        localization.setCityName(cityName);
        localization.setCountry(country);
        localization.setLongitude(longitude);
        localization.setLatitude(latitude);

        return localizationRepository.save(localization);
    }
}
