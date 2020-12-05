package com.sda.weather.localization;

import com.sda.weather.exceptions.InternalServerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LocalizationCreateServiceTest {

    @Mock
    LocalizationRepository localizationRepository;
    @InjectMocks
    LocalizationCreateService localizationCreateService;

    @Test
    void createLocalizationTest_callsLocalizationRepository() {
        localizationRepository.deleteAll();
        // given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        // when
        Localization localization = localizationCreateService
                .createLocalization
                        (new LocalizationDefinition("Gdansk", "Pomerania", "Poland", 69, 69));
        // then
        verify(localizationRepository).save(any(Localization.class));
        assertThat(localization).isInstanceOf(Localization.class);
    }

    @Test
    void createLocalization_whenRegionIsBlank_savesLocalizationInTheRepositoryWithNullValue() {
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        //when
        Localization localization = localizationCreateService
                .createLocalization
                        (new LocalizationDefinition("Gdansk", " ", "Poland", 69, 69));
        //then
        assertThat(localization).isInstanceOf(Localization.class);
        verify(localizationRepository).save(new Localization(null, "Gdansk", null, "Poland", 69, 69));
    }
}
