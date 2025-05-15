package com.open.weather.api.openweatherapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WeatherResponse {

    private Request request;
    private Location location;
    private Current current;
}
