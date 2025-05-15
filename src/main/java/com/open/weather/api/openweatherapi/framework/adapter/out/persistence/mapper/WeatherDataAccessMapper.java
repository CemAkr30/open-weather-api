package com.open.weather.api.openweatherapi.framework.adapter.out.persistence.mapper;

import com.open.weather.api.openweatherapi.domain.model.Weather;
import com.open.weather.api.openweatherapi.framework.adapter.out.persistence.entity.WeatherEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataAccessMapper {

    public Weather createWeatherToWeatherEntity(WeatherEntity weatherEntity) {
        return Weather.builder()
                .country(weatherEntity.getCountry())
                .temperature(weatherEntity.getTemperature())
                .cityName(weatherEntity.getCityName())
                .requestedCity(weatherEntity.getRequestedCityName())
                .localTime(weatherEntity.getResponseLocalTime())
                .updatedAt(weatherEntity.getUpdatedTime())
                .build();
    }

    public WeatherEntity createWeatherEntityToWeather(Weather weather) {
        return new WeatherEntity(
                weather.getId().getValue().toString(),
                weather.getRequestedCity(),
                weather.getCityName(),
                weather.getCountry(),
                weather.getTemperature(),
                weather.getUpdatedAt(),
                weather.getLocalTime()
        );
    }
}
