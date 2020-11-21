package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalizationController {
    final LocalizationFetchService locFetchService;
    final LocalizationMapper entryMapper;

    @GetMapping("/getloc/{id}")
    LocalizationDto getLocalization(@PathVariable Long id) {
        Localization localization = locFetchService.fetchLocalization(id);
        return entryMapper.mapToLocalizationDto(localization);
    }
}
