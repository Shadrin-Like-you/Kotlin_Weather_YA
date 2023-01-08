package com.shadrin.kotlin_weather_ya.model.DTO


import com.google.gson.annotations.SerializedName

data class WeatherDTO(

    val fact: Fact,
    val forecast: Forecast,
    val info: Info,
    val now: Int,
    @SerializedName("now_dt")
    val nowDt: String
)