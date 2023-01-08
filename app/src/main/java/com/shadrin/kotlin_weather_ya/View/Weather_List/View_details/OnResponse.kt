package com.shadrin.kotlin_weather_ya.View.Weather_List.View_details


import com.shadrin.kotlin_weather_ya.model.DTO.WeatherDTO

fun interface OnResponse {
    fun onResponse(weather: WeatherDTO)
}