package com.open.weather.api.openweatherapi.domain.exception;

import com.open.weather.api.openweatherapi.common.domain.exception.DomainException;

public class WeatherNotFoundException extends DomainException {
    
    public WeatherNotFoundException(String message) {
        super(message);
    }

    public WeatherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
