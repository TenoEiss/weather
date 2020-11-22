package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationFetchAllService localizationFetchAllService;
    final LocalizationFetchService locFetchService;
    final LocalizationCreateService localizationCreateService;
    final LocalizationMapper localizationMapper;

    @GetMapping("/loc")
    List<LocalizationDto> getAllLocalizations() {
        List<LocalizationDto> localizationDtoList = new LinkedList<>();
        List<Localization> localizationList = localizationFetchAllService.fetchAllLocalizations();
        for (Localization localization : localizationList
        ) {
            localizationDtoList.add(localizationMapper.mapToLocalizationDto(localization));
        }
        return localizationDtoList;
    }

    @GetMapping("/loc/{id}")
    LocalizationDto getLocalization(@PathVariable Long id) {
        Localization localization = locFetchService.fetchLocalization(id);
        return localizationMapper.mapToLocalizationDto(localization);
    }

    @PostMapping("/loc")
    ResponseEntity<LocalizationDefinition> postLocalization(@RequestBody LocalizationDto localizationDto) {

        Localization localization = localizationCreateService
                .createLocalization(localizationMapper.mapToLocalizationDefinition(localizationDto));
        log.info(localization + "some extra info" +
                " that is so necessary to understand how good the situation is");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDefinition(localizationDto));
    }
}
