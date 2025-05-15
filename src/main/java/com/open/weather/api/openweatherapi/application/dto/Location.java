package com.open.weather.api.openweatherapi.application.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Location {

    private String name;
    private String country;
    private String region;
    private Double lat;
    private Double lon;

    @JsonProperty("timezone_id")
    private String timezoneId;

    private String localtime;

    @JsonProperty("localtime_epoch")
    private String localtimeEpoch;

    @JsonProperty("utc_offset")
    private String utcOffset;
}