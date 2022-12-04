package com.shadrin.kotlin_weather_ya.View.Weather_List.View_details

import com.shadrin.kotlin_weather_ya.domain.Weather

fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}