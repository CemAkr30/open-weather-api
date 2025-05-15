package com.open.weather.api.openweatherapi.framework.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.open.weather.api.openweatherapi.domain.WeatherDomainService;
import com.open.weather.api.openweatherapi.domain.WeatherDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Modifier;
import java.time.Clock;

@Configuration
public class BeanConfiguration {

    @Bean
    public WeatherDomainService weatherDomainService() {
        return new WeatherDomainServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL)
                .create();
    }
}
