package com.sda.weather.localization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    @Test
    void test() throws Exception {
        Localization localization = new Localization();
        localization.setCountry("Poland");
        localization.setCityName("Warsaw");
        localization.setLatitude(10);
        localization.setLongitude(10);
        Localization savedLocalization = localizationRepository.save(localization);
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder request = get("/localization/" + id + "/forecast");

        mockMvc.perform(request).andReturn().getResponse();
    }
}
