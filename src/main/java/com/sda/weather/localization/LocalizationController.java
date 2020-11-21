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
    final LocalizationMapper entryMapper;

    @GetMapping("/loc/{id}")
    LocalizationDto getLocalization(@PathVariable Long id) {
        Localization localization = locFetchService.fetchLocalization(id);
        return entryMapper.mapToLocalizationDto(localization);
    }

//    Mam 100 pytań:
//    1. Czemu @ResponseBody jest not applicable to parameter
//    2. W Post mappingu wykorzystuję LocaDefinition, ale podaję LocaDto, jak to powinno faktycznie wyglądać?
//    3. Skoro muszę zamienić Localization na LocalizationDto by użytkownik nie miał dostępu do Localization,
//       to czemu localization cały czas przewija mi się w tej metodzie?
//    Proszę o 2 minuty wyjaśnienia co ma się zamienić w co. ResponseEntity to dla mnie jeszcze zagadka.
    @PostMapping("/loc")
    ResponseEntity<LocalizationDto> postLocalization(@ResponseBody LocalizationDto localizationDto) throws Exception {
        Localization localization = localizationCreateService.createLocalization(LocalisationDefinition.builder().build());
        log.info(String.valueOf(localization));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entryMapper.mapToLocalizationDto(localization));
    }
}
