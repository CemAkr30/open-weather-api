package com.open.weather.api.openweatherapi.domain;

import com.open.weather.api.openweatherapi.domain.model.Weather;

import java.time.Clock;

public interface WeatherDomainService {

    void isStale(Weather weather, Clock clock, long ttlMinutes);
}
