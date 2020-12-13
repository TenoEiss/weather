package com.sda.weather.localization;

import com.sda.weather.exceptions.BadRequestException;
import com.sda.weather.exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    Localization createLocalization(LocalizationDefinition localizationDefinition) {
        float latitude = localizationDefinition.getLatitude();
        float longitude = localizationDefinition.getLongitude();
        String country = localizationDefinition.getCountry();
        String cityName = localizationDefinition.getCityName();
        if (cityName == null || country == null || cityName.isBlank() || country.isBlank() || longitude > 180 || longitude < -180 || latitude > 90 || latitude < -90) {
            throw new BadRequestException("Data provided in the creation query is incorrect");
        }
        Localization localization = new Localization();
        Optional.of(localizationDefinition)
                .map(loc -> loc.getRegion())
                .filter(region -> !region.isBlank())
                .ifPresent(region -> localization.setRegion(region));

        localization.setCityName(cityName);
        localization.setCountry(country);
        localization.setLongitude(longitude);
        localization.setLatitude(latitude);

        return localizationRepository.save(localization);
    }
}
