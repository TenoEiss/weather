package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cityName;
    private String region;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private float longitude;
    @Column(nullable = false)
    private float latitude;

    public Optional<String> getRegion() {
        return Optional.ofNullable(this.region);
    }
}
