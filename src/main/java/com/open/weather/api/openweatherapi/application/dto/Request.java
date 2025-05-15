package com.open.weather.api.openweatherapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Request {
    private String type;
    private String query;
    private String language;
    private String unit;
}