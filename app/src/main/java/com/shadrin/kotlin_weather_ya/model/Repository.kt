package com.shadrin.kotlin_weather_ya.model

import com.shadrin.kotlin_weather_ya.domain.Weather

fun interface RepositorySingle {
    fun getWeather(lat: Double, lon: Double): Weather

}

fun interface RepositoryAll {
    fun getListWeather(location: Location): List<Weather>

}

sealed class Location {
    object Russian : Location()
    object World : Location()
}