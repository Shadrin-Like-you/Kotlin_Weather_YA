package com.shadrin.kotlin_weather_ya.model.DTO


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("url")
    val url: String
)