package com.open.weather.api.openweatherapi.application.ports.in.service;

import com.open.weather.api.openweatherapi.application.dto.WeatherDto;

public interface WeatherApplicationServicePort {

    WeatherDto getWeather(String city);

    WeatherDto createCityWeather(String city);
}
