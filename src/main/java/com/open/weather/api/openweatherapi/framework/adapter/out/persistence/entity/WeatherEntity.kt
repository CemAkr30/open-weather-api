package com.open.weather.api.openweatherapi.framework.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class WeatherEntity @JvmOverloads constructor(
    @Id
    val id: String? = "",
    val requestedCityName: String,
    val cityName: String,
    val country: String,
    val temperature: Int,
    val updatedTime: LocalDateTime,
    val responseLocalTime: LocalDateTime
)