package com.open.weather.api.openweatherapi.framework.adapter.out.api;

import com.google.gson.Gson;
import com.open.weather.api.openweatherapi.application.dto.WeatherResponse;
import com.open.weather.api.openweatherapi.application.ports.out.api.WeatherApiPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.open.weather.api.openweatherapi.framework.constants.Constant.*;

@Component
@RequiredArgsConstructor
@Log4j2
public class WeatherStackApiAdapter implements WeatherApiPort {

    private final RestTemplate restTemplate;
    private final Gson gson;

    @Override
    public WeatherResponse fetchWeather(String city) {
        log.info("Requesting weather stack api for city: {}", city);
        String url = getWeatherStackUrl(city);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        return gson.fromJson(responseEntity.getBody(), WeatherResponse.class);
    }

    private String getWeatherStackUrl(String city) {
        return WEATHER_STACK_API_BASE_URL +
                WEATHER_STACK_API_ACCESS_KEY_PARAM +
                API_KEY +
                WEATHER_STACK_API_QUERY_PARAM +
                city;
    }
}


