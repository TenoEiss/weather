package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationFetchService locFetchService;
    final LocalizationCreateService localizationCreateService;
    final LocalizationMapper localizationMapper;

    @GetMapping("/loc/{id}")
    LocalizationDto getLocalization(@PathVariable Long id) {
        Localization localization = locFetchService.fetchLocalization(id);
        return localizationMapper.mapToLocalizationDto(localization);
    }
//    Mam 1 pytanie:
//    2. W Post mappingu wykorzystuję LocaDefinition, ale podaję LocaDto, jak to powinno faktycznie wyglądać?
//    Proszę o 1 minutę wyjaśnienia co ma się zamienić w co. ResponseEntity to dla mnie jeszcze zagadka.
//    Na chwilę obecną nie jestem zabezpieczony i mogę dostać nullpointer jak ktoś nie poda regionu. Jak to zrobić dobrze?
    @PostMapping("/loc")
    ResponseEntity<LocalizationDto> postLocalization(@RequestBody LocalizationDto localizationDto) {
        Localization localization = localizationCreateService.createLocalization(LocalizationDefinition
                .builder()
                .cityName(localizationDto.getCityName())
                .region(localizationDto.getRegion())
                .country(localizationDto.getCountry())
                .longitude(localizationDto.getLongitude())
                .latitude(localizationDto.getLatitude())
                .build());
        log.info(String.valueOf(localization));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(localization));
    }
}
