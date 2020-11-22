package com.sda.weather.localization;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Entity
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private String region;
    private String country;
    private float longitude;
    private float latitude;


    public Optional<String> getRegion(){
        return Optional.ofNullable(this.region);
    }
}
