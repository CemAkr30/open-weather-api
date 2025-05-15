package com.open.weather.api.openweatherapi.framework.adapter.in.rest;

import com.open.weather.api.openweatherapi.application.dto.WeatherDto;
import com.open.weather.api.openweatherapi.application.ports.in.service.WeatherApplicationServicePort;
import com.open.weather.api.openweatherapi.framework.adapter.in.rest.validation.CityNameConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/open-weather")
@Validated
@Tag(
        name = "Open Weather Service API v1",
        description = "Open Weather Service API to search the current weather report of the city"
)
public class WeatherController {

    private final WeatherApplicationServicePort weatherService;

    @Operation(
            method = "GET",
            summary = "Search the current weather report of the city",
            description = "Search the current weather report of the city name filter. The API has rate limiting: 10 requests per minute",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The current weather report of the city",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = WeatherDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is wrong. Re-try with a valid city name",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "429",
                            description = "Rate limit exceeded. Please try your request again later!",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal/External server error.",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable @CityNameConstraint String city) {
        return ResponseEntity.ok(weatherService.getWeather(city));
    }

    @Operation(
            method = "POST",
            summary = "Create and cache weather data for the city",
            description = "Fetches weather from WeatherStack API and caches the response using city as key.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully fetched and cached weather data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = WeatherDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City name is invalid",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server error",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("/{city}")
    public ResponseEntity<WeatherDto> createWeather(@PathVariable @CityNameConstraint String city) {
        return ResponseEntity.ok(weatherService.createCityWeather(city));
    }
}

