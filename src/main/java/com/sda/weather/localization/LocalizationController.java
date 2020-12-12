package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return localizationFetchAllService.fetchAllLocalizations().stream()
                .map(localizationMapper::mapToLocalizationDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/loc/{id}")
    LocalizationDto getLocalization(@PathVariable Long id) {
        Localization localization = locFetchService.fetchLocalization(id); // todo merge with localizationFetchAllService
        return localizationMapper.mapToLocalizationDto(localization);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/loc")
    ResponseEntity<LocalizationDto> postLocalization(@RequestBody LocalizationDto localizationDto) {
        Localization localization = localizationCreateService
                .createLocalization(localizationMapper.mapToLocalizationDefinition(localizationDto));

        log.info(localization + "some extra info" +
                " that is so necessary to understand how good the situation is");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(localization));
    }
}
