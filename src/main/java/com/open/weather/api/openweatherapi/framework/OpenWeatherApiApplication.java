package com.open.weather.api.openweatherapi.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.open.weather.api.openweatherapi.*"
})
@EnableCaching
@EnableScheduling
public class OpenWeatherApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenWeatherApiApplication.class, args);
    }

}
