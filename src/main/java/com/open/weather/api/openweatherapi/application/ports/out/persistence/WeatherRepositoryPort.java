package com.open.weather.api.openweatherapi.application.ports.out.persistence;

import com.open.weather.api.openweatherapi.domain.model.Weather;

import java.util.Optional;

public interface WeatherRepositoryPort {

    Optional<Weather> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);

    Weather weatherSave(Weather weather);
}
