package com.open.weather.api.openweatherapi.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "weather-stack")
public class WeatherStackConfigData {

    private String apiUrl;
    private String apiKey;
    private Integer apiCallLimit;
    private String cacheName;
    private Integer cacheTtl;
}
