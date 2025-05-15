package com.open.weather.api.openweatherapi.framework.adapter.in.rest.exception;

public class WeatherStackApiException extends RuntimeException {
    
    public WeatherStackApiException(String message) {
        super(message);
    }

    public WeatherStackApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
