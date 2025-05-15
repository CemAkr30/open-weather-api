package com.open.weather.api.openweatherapi.application.helper;

import com.open.weather.api.openweatherapi.application.config.WeatherStackConfigData;
import com.open.weather.api.openweatherapi.application.dto.WeatherResponse;
import com.open.weather.api.openweatherapi.application.mapper.WeatherApplicationMapper;
import com.open.weather.api.openweatherapi.application.ports.out.persistence.WeatherRepositoryPort;
import com.open.weather.api.openweatherapi.domain.WeatherDomainService;
import com.open.weather.api.openweatherapi.domain.model.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Log4j2
@Component
@RequiredArgsConstructor
public class WeatherHelper {

    private final WeatherStackConfigData weatherStackConfigData;
    private final WeatherDomainService domainService;
    private final WeatherApplicationMapper weatherApplicationMapper;
    private final WeatherRepositoryPort repository;
    private final Clock clock;

    public Weather saveWeatherEntity(String city, WeatherResponse response) {
        Weather weather = weatherApplicationMapper.createWeatherResponseToWeather(city,
                getLocalDateTimeNow(),
                response);

        domainService.isStale(weather, clock, weatherStackConfigData.getCacheTtl());

        return repository.weatherSave(weather);
    }

    public boolean isOutdated(LocalDateTime updatedAt) {
        return updatedAt.isBefore(getLocalDateTimeNow().minusMinutes(
                weatherStackConfigData.getApiCallLimit())
        );
    }

    public LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, clock.getZone());
    }
}
