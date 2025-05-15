package com.open.weather.api.openweatherapi.domain.exception;

import com.open.weather.api.openweatherapi.common.domain.exception.DomainException;

public class WeatherDomainException extends DomainException {
    
    public WeatherDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherDomainException(String message) {
        super(message);
    }
}
