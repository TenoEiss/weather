package com.sda.weather.localization;

import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalizationFetchAllService {

    private final LocalizationRepository localizationRepository;

    public List<Localization> fetchAllLocalizations() {
        if (localizationRepository.findAll().isEmpty()) {
            throw new NotFoundException("No known places exist in the world, sorry!");
        }
        return localizationRepository.findAll();
    }
}
