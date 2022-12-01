package com.shadrin.kotlin_weather_ya.model

import com.shadrin.kotlin_weather_ya.VM.AppState
import com.shadrin.kotlin_weather_ya.domain.Weather
import com.shadrin.kotlin_weather_ya.domain.getRussianCities
import com.shadrin.kotlin_weather_ya.domain.getWorldCities
import com.shadrin.kotlin_weather_ya.utils.LOCATION_RF

class RepositoryLocalImpl : RepositoryAll,RepositorySingle {
    override fun getListWeather(location: Location): List<Weather> {

        return when (location) {
            Location.Russian -> {
                getRussianCities()
            }
            Location.World -> {
                getWorldCities()
            }
        }
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }


}