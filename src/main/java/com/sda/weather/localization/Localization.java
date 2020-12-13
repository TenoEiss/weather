package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private String createdBy;

    public Localization(Long id, String cityName, String region, String country, float longitude, float latitude) {
        this.id = id;
        this.cityName = cityName;
        this.region = region;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Optional<String> getRegion() {
        return Optional.ofNullable(this.region);
    }
}
