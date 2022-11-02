package com.shadrin.kotlin_weather_ya.model

import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.domain.Weather

class RepositoryLocalImpl:Repository {
    override fun getListWeather(): List<Weather> {

       return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }

    override fun getWeatherFromServer(): Weather {
        TODO("Not yet implemented")
    }

    override fun getWeatherFromLocalStorage(): Weather {
        TODO("Not yet implemented")
    }

}