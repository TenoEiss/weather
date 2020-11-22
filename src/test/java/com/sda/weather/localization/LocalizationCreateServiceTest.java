package com.sda.weather.localization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LocalizationCreateServiceTest {

    @Mock
    LocalizationRepository localizationRepository;

    @InjectMocks
    LocalizationCreateService localizationCreateService;

    @Test
    void createLocalizationTest_callsRepository() {
        localizationRepository.deleteAll();
        // given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        LocalizationDefinition localizationDefinition =
                new LocalizationDefinition("Gdansk", "Pomerania", "Poland", 69f, 69f);

        // when
        Localization result = localizationCreateService.createLocalization(localizationDefinition);

        // then

    }
}