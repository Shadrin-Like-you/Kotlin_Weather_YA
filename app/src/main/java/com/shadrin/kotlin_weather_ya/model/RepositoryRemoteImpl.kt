package com.shadrin.kotlin_weather_ya.model

import com.shadrin.kotlin_weather_ya.domain.Weather

class RepositoryRemoteImpl : RepositorySingle, RepositoryAll {

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }

    override fun getListWeather(location: Location): List<Weather> {
        TODO("Not yet implemented")
    }

    /*  override fun getWeatherFromServer(): Weather {
          Thread {
              Thread.sleep(2000L)
          }.start()
          return Weather()
      }

      override fun getWeatherFromLocalStorage(): Weather {
          Thread {
              Thread.sleep(2000L)
          }.start()
          return Weather()
      }

     */
}