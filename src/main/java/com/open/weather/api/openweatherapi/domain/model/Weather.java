package com.open.weather.api.openweatherapi.domain.model;

import com.open.weather.api.openweatherapi.common.domain.entity.AggregateRoot;
import com.open.weather.api.openweatherapi.common.domain.valueobject.WeatherId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
public class Weather extends AggregateRoot<WeatherId> {

    private final String requestedCity;
    private final String cityName;
    private final String country;
    private final int temperature;
    private final LocalDateTime updatedAt;
    private final LocalDateTime localTime;

    public void init() {
        super.setId(new WeatherId(UUID.randomUUID()));
    }
}
