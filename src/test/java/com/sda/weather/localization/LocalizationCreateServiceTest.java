package com.sda.weather.localization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LocalizationCreateServiceTest {

    @Mock
    Localization localization;
    @Mock
    LocalizationRepository localizationRepository;
    @Mock
    LocalizationDefinition localizationDefinition;
    @InjectMocks
    LocalizationCreateService localizationCreateService;

    @Test
    void createLocalizationTest_callsLocalizationRepository() {
        localizationRepository.deleteAll();
        // given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        // when
        localization = localizationCreateService.createLocalization(localizationDefinition);
        // then
        verify(localizationRepository).save(any(Localization.class));
        assertThat(localization).isInstanceOf(Localization.class);
    }
}