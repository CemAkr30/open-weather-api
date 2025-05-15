package com.open.weather.api.openweatherapi.application;

import com.open.weather.api.openweatherapi.application.dto.WeatherDto;
import com.open.weather.api.openweatherapi.application.dto.WeatherResponse;
import com.open.weather.api.openweatherapi.application.helper.WeatherHelper;
import com.open.weather.api.openweatherapi.application.mapper.WeatherApplicationMapper;
import com.open.weather.api.openweatherapi.application.ports.in.service.WeatherApplicationServicePort;
import com.open.weather.api.openweatherapi.application.ports.out.api.WeatherApiPort;
import com.open.weather.api.openweatherapi.application.ports.out.persistence.WeatherRepositoryPort;
import com.open.weather.api.openweatherapi.domain.exception.WeatherNotFoundException;
import com.open.weather.api.openweatherapi.domain.model.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"weathers"})
public class WeatherApplicationServicePortImpl implements WeatherApplicationServicePort {

    private final WeatherApiPort weatherApi;
    private final WeatherRepositoryPort repository;
    private final WeatherApplicationMapper weatherApplicationMapper;
    private final WeatherHelper weatherHelper;

    @Override
    @Cacheable(key = "#city")
    public WeatherDto getWeather(String city) {
        return repository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city)
                .map(weather -> {
                    if (weatherHelper.isOutdated(weather.getUpdatedAt())) {
                        log.info("Weather data for '{}' is outdated. Refreshing from API.", city);
                        return createCityWeather(city);
                    }
                    log.info("Weather data for '{}' is up-to-date. Returning from DB.", city);
                    return weatherApplicationMapper.createWeatherDtoToWeather(weather);
                })
                .orElseThrow(() -> new WeatherNotFoundException("Weather not found for city: " + city));
    }

    @Override
    @CachePut(key = "#city")
    public WeatherDto createCityWeather(String city) {
        log.info("Calling WeatherStack API for city: {}", city);
        WeatherResponse weatherResponse = weatherApi.fetchWeather(city);
        Weather savedWeather = weatherHelper.saveWeatherEntity(city, weatherResponse);
        return weatherApplicationMapper.createWeatherDtoToWeather(savedWeather);
    }

    @Scheduled(fixedRateString = "${weather-stack.cache-ttl}")
    @CacheEvict(allEntries = true)
    public void clearCache() {
        log.info("Weather cache cleared.");
    }
}

