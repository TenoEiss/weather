package com.sda.weather.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LocalizationFetchIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;
    ObjectMapper objectMapper = new ObjectMapper();

//    So far tylko success integration test, todo shtf test i cała reszta
    @Test
    void createNewLocalization_SaveLocalizationInRepositoryAndReturn200HttpStatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "Gdansk", "Pomerania", "Poland", 69f, 69f);
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/loc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        // when
        MvcResult result = mockMvc.perform(post).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Localization> entries = localizationRepository.findAll();
        assertThat(entries.size()).isEqualTo(1);
        assertThat(entries.get(0)).satisfies(localization -> {
            assertThat(localization.getCityName()).isEqualTo("Gdansk");
            assertThat(localization.getRegion()).isEqualTo("Pomerania");
            assertThat(localization.getCountry()).isEqualTo("Poland");
            assertThat(localization.getLongitude()).isBetween(-180f, 180f);
            assertThat(localization.getLatitude()).isBetween(-90f, 90f);
        });
    }

}

