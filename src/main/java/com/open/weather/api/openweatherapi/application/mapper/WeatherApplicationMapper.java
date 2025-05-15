package com.open.weather.api.openweatherapi.application.mapper;

import com.open.weather.api.openweatherapi.application.dto.WeatherDto;
import com.open.weather.api.openweatherapi.application.dto.WeatherResponse;
import com.open.weather.api.openweatherapi.domain.model.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherApplicationMapper {

    public WeatherDto createWeatherDtoToWeather(Weather weather) {
        return new WeatherDto(
                weather.getCityName(),
                weather.getCountry(),
                weather.getTemperature(),
                weather.getUpdatedAt()
        );
    }

    public Weather createWeatherResponseToWeather(String city,
                                                  LocalDateTime localDateTime,
                                                  WeatherResponse response) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return Weather.builder()
                .requestedCity(city)
                .cityName(response.getLocation().getName())
                .country(response.getLocation().getCountry())
                .temperature(response.getCurrent().getTemperature())
                .localTime(localDateTime)
                .updatedAt(LocalDateTime.parse(response.getLocation().getLocaltime(), formatter))
                .build();
    }
}
