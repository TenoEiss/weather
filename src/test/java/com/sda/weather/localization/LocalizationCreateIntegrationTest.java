package com.sda.weather.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LocalizationCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        localizationRepository.deleteAll();
    }

    @Test
    void createLocalization_returnsLocalizationsAndCREATEDStatusCode() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, "Gdansk", "Pomerania", "Poland", 69, 69);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        LocalizationDto responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), LocalizationDto.class);
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody).extracting(LocalizationDto::getCityName, LocalizationDto::getCountry).containsExactly("Gdansk", "Poland");
        assertThat(localizationRepository.findAll()).singleElement().satisfies(localization -> {//todo .singleElement not working wtf is that?
            assertThat(localization.getId()).isNotNull();
            assertThat(localization.getCityName()).isEqualTo("Gdansk");
            assertThat(localization.getCountry()).isEqualTo("Poland");
            assertThat(localization.getRegion()).hasValue("Pomerania");
            assertThat(localization.getLongitude()).isEqualTo(69);
            assertThat(localization.getLatitude()).isEqualTo(69);
        });
    }

    @Test
    void createLocalization_whenRegionIsNull_returnLocalizationsAnd200StatusCode() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, "Gdansk", null, "Poland", 69, 69);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        LocalizationDto responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), LocalizationDto.class);
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody).extracting(LocalizationDto::getCityName, LocalizationDto::getCountry).containsExactly("Gdansk", "Poland");
        assertThat(localizationRepository.findAll()).singleElement().satisfies(localization -> {
            assertThat(localization.getId()).isNotNull();
            assertThat(localization.getCityName()).isEqualTo("Gdansk");
            assertThat(localization.getCountry()).isEqualTo("Poland");
            assertThat(localization.getRegion()).isEmpty();
            assertThat(localization.getLongitude()).isEqualTo(69);
            assertThat(localization.getLatitude()).isEqualTo(69);
        });
    }

    @Test
    void createLocalization_whenCityIsNull_return400StatusCOde() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, null, "Pomerania", "Poland", 69, 69);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localizationRepository.findAll()).isEmpty();
    }
    @Test
    void createLocalization_whenCountryIsNull_return400StatusCOde() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, "Gdansk", "Pomerania", null, 69, 69);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localizationRepository.findAll()).isEmpty();
    }
    @Test
    void createLocalization_whenLongitudeValueOver180_return400StatusCOde() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, "Gdansk", "Pomerania", "Poland", 181, 69);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localizationRepository.findAll()).isEmpty();
    }
    @Test
    void createLocalization_whenLongitudeValueUnderMinus180_return400StatusCOde() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, "Gdansk", "Pomerania", "Poland", -181, 69);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localizationRepository.findAll()).isEmpty();
    }
    @Test
    void createLocalization_whenLatitudeValueOver90_return400StatusCOde() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, "Gdansk", "Pomerania", "Poland", 69, 96);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localizationRepository.findAll()).isEmpty();
    }
    @Test
    void createLocalization_whenLatitudeValueUnderMinus90_return400StatusCOde() throws Exception {
        //given
        LocalizationDto requestBody =
                new LocalizationDto(null, "Gdansk", "Pomerania", "Poland", 69, -96);
        MockHttpServletRequestBuilder request = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localizationRepository.findAll()).isEmpty();
    }
}
