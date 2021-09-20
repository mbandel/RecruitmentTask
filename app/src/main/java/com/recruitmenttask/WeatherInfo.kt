package com.recruitmenttask

data class WeatherInfo(
    val city: String,
    val hourly_temp: List<HourlyTemp>,
    val weather: String
)