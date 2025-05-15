package com.open.weather.api.openweatherapi.application.ports.out.api;

import com.open.weather.api.openweatherapi.application.dto.WeatherResponse;

public interface WeatherApiPort {

    WeatherResponse fetchWeather(String city);
}
