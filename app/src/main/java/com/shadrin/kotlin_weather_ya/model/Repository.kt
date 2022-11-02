package com.shadrin.kotlin_weather_ya.model

import com.shadrin.kotlin_weather_ya.domain.Weather

interface Repository {
    fun getListWeather(): List<Weather>
    fun getWeather(lat: Double, lon: Double): Weather
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}