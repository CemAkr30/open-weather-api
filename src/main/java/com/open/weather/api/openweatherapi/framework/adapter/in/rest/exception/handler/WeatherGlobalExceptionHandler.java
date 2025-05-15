package com.open.weather.api.openweatherapi.framework.adapter.in.rest.exception.handler;

import com.open.weather.api.openweatherapi.common.application.handler.ErrorDTO;
import com.open.weather.api.openweatherapi.common.application.handler.GlobalExceptionHandler;
import com.open.weather.api.openweatherapi.domain.exception.WeatherNotFoundException;
import com.open.weather.api.openweatherapi.framework.adapter.in.rest.exception.WeatherStackApiException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
@Hidden
public class WeatherGlobalExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(WeatherStackApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handle(WeatherStackApiException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(WeatherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handle(WeatherNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }
}
