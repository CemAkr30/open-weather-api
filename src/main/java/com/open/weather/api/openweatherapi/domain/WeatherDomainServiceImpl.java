package com.open.weather.api.openweatherapi.domain;

import com.open.weather.api.openweatherapi.domain.exception.WeatherDomainException;
import com.open.weather.api.openweatherapi.domain.model.Weather;

import java.time.Clock;
import java.time.LocalDateTime;

public class WeatherDomainServiceImpl implements WeatherDomainService {

    @Override
    public void isStale(Weather weather, Clock clock, long ttlMinutes) {
        weather.init();
        boolean status = weather.getUpdatedAt()
                .isBefore(LocalDateTime.now(clock).minusMinutes(ttlMinutes));
        if (status) {
            throw new WeatherDomainException("Weather domain stale.");
        }
    }
}
